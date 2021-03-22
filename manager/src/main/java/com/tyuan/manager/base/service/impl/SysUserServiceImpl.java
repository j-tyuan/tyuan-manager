package com.tyuan.manager.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.common.utils.TreeUtils;
import com.tyuan.dao.base.customize.CSysUserMapper;
import com.tyuan.dao.base.customize.CSysUserRoleMapper;
import com.tyuan.dao.base.mapper.OrganizationInstitutionMapper;
import com.tyuan.dao.base.mapper.SysRoleMapper;
import com.tyuan.manager.base.cache.LocalCache;
import com.tyuan.manager.base.cache.UserInfoCacheService;
import com.tyuan.manager.base.service.SysUserAvatarService;
import com.tyuan.manager.base.service.SysUserService;
import com.tyuan.manager.base.utils.DateUtil;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.pojo.*;
import com.tyuan.model.base.pojo.custom.COrganizationInstitution;
import com.tyuan.model.base.vo.DataTableParam;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.SysUserTableParamsVo;
import com.tyuan.model.base.vo.sys.SysUserVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private CSysUserMapper cSysUserMapper;

    @Resource
    private CSysUserRoleMapper csysUserRoleMapper;

    @Resource
    private SysUserAvatarService sysUserAvatarService;

    @Resource
    OrganizationInstitutionMapper organizationInstitutionMapper;

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    UserInfoCacheService userInfoCacheService;

    public SysUserExample getUserExampleByParams(SysUserTableParamsVo param) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        boolean flag = false;
        if (StringUtils.isNotBlank(param.getUserNo())) {
            criteria.andUserNoEqualTo(param.getUserNo());
            flag = true;
        }
        String like = "%{0}%";
        if (StringUtils.isNotBlank(param.getName())) {
            criteria.andNameLike(MessageFormat.format(like, param.getName()));
            flag = true;
        }
        if (StringUtils.isNotBlank(param.getAccount())) {
            criteria.andAccountEqualTo(param.getAccount());
            flag = true;
        }
        if (StringUtils.isNotBlank(param.getPhone())) {
            criteria.andPhoneEqualTo(param.getPhone());
            flag = true;
        }
        Date loginDate = DateUtil.format(param.getLoginDate());
        if (null != param.getLoginDate()) {
            criteria.andLoginDateGreaterThanOrEqualTo(loginDate);
            flag = true;
        }
        // 如果没有其它 必要的查询参数 且机构id不为空的话，就按照机构来查询，否则就去除机构这个条件
        if (!flag && null != param.getInstId()) {
            List<COrganizationInstitution> list = LocalCache.SYS_INSTITUTION.getData();
            List<COrganizationInstitution> child = TreeUtils.getChild(list, param.getInstId());
            List<Long> ids = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(child)) {
                ids.add(param.getInstId());
                child.forEach(e -> {
                    ids.add(e.getId());
                });
                criteria.andInstIdIn(ids);
            } else {
                criteria.andInstIdEqualTo(param.getInstId());
            }
        }
        // 只允许查看普通用户
        criteria.andUserTypeNotEqualTo(USER_TYPE.SYS.getType());
        return example;
    }

    @Override
    public PageInfo getByExample(SysUserExample sysUserExample, DataTableParam param) {
        PageHelper.offsetPage(param.getOffset(), param.getPageSize()).setOrderBy("update_date desc");
        List<SysUser> result = cSysUserMapper.selectByExample(sysUserExample);
        PageInfo pageInfo = new PageInfo<>(result);
        List<Map> newUserList = userPostProcessor(result);
        pageInfo.setList(newUserList);
        return pageInfo;
    }


    @Override
    public PageInfo getByParams(SysUserTableParamsVo param) {
        SysUserExample sysUserExample = getUserExampleByParams(param);
        return getByExample(sysUserExample, param);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysUserVo sysUser) throws ServiceException {
        OrganizationInstitution institution = organizationInstitutionMapper.selectByPrimaryKey(sysUser.getInstId());
        if (null == institution) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "机构未找到");
        }
        sysUser.setInstName(institution.getInstName());
        sysUser.setCreateBy(UserInfoHolder.getUserName());
        sysUser.setUpdateBy(UserInfoHolder.getUserName());
        String pass = sysUser.getPassword();
        if (StringUtils.isBlank(pass)) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "密码不许为空");
        }

        SysUserExample example = new SysUserExample();
        example.or().andAccountEqualTo(sysUser.getAccount());
        List<SysUser> list = cSysUserMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "账号重复");
        }

        pass = DigestUtils.md5DigestAsHex(pass.getBytes());
        sysUser.setPassword(pass);

        // 只允许创建普通用户
        sysUser.setUserType(USER_TYPE.ORDINARY.getType());
        cSysUserMapper.insertSelective(sysUser);

        // 插入完成后，得到最后一个ID，根据最后一个ID生成员工编号
        String c = String.valueOf(sysUser.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("T");
        for (int i = 5; i > c.length(); i--) {
            sb.append("0");
        }
        sb.append(c);
        SysUser newUser = new SysUser();
        newUser.setUserNo(sb.toString());
        newUser.setId(sysUser.getId());
        cSysUserMapper.updateByPrimaryKeySelective(newUser);

        List<Long> roles = sysUser.getRoleIds();
        if (CollectionUtils.isNotEmpty(roles)) {
            cSysUserMapper.bindRole(sysUser.getId(), roles);
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysUserVo sysUser) throws ServiceException {

        Long id = sysUser.getId();
        SysUser val = cSysUserMapper.selectByPrimaryKey(id);
        if (val == null) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "未找到数据，修改失败");
        }
        String pass = sysUser.getPassword();
        if (StringUtils.isNotBlank(pass)) {
            pass = DigestUtils.md5DigestAsHex(pass.getBytes());
            sysUser.setPassword(pass);
        } else {
            sysUser.setPassword(null);
        }
        Long instId = sysUser.getInstId();
        if (null != instId) {
            OrganizationInstitution institution = organizationInstitutionMapper.selectByPrimaryKey(instId);
            if (null == institution) {
                throw new ServiceException(ErrorCodeConsts.ERROR, "机构未找到");
            }
            sysUser.setInstName(institution.getInstName());
        }
        sysUser.setAccount(null);
        sysUser.setUserNo(null);
        sysUser.setUpdateBy(UserInfoHolder.getUserName());

        // 只允许修改普通用户
        sysUser.setUserType(USER_TYPE.ORDINARY.getType());
        cSysUserMapper.updateByPrimaryKeySelective(sysUser);

        // 先解除绑定后在绑定
        cSysUserMapper.unBindByUserId(sysUser.getId());
        if (CollectionUtils.isNotEmpty(sysUser.getRoleIds())) {
            cSysUserMapper.bindRole(sysUser.getId(), sysUser.getRoleIds());
        }
    }

    @Override
    public SysUser getByAccount(String account) {
        SysUserExample example = new SysUserExample();
        example.createCriteria()
                .andAccountEqualTo(account)
                .andUserTypeNotEqualTo(USER_TYPE.ORDINARY.getType());
        List<SysUser> list = cSysUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
        ids.forEach(id -> {
            cSysUserMapper.deleteByPrimaryKey(id);
            // 清除用户权
            cSysUserMapper.unBindByUserId(id);
        });
    }

    @Override
    public SysUser getById(Long id) {
        SysUser sysUser = cSysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null) {
            return null;
        }
        sysUser.setPassword(null);
        return sysUser;
    }

    @Override
    public List fetch(String value) {
        SysUserExample example = new SysUserExample();
        List<SysUser> list;
        Pattern pattern = Pattern.compile("^\\d{1,11}$");
        if (pattern.matcher(value).find()) {
            Long l = Long.parseLong(value);
            example.or().andIdEqualTo(l);
            // 查找手机号
            example.or().andPhoneLike(MessageFormat.format("{0}%", value));
            list = cSysUserMapper.selectByExample(example);
        } else {
            String like = "%{0}%";
            example.or().andNameLike(MessageFormat.format(like, value));
            example.or().andAccountLike(MessageFormat.format(like, value));
            // 工号
            list = cSysUserMapper.selectByExample(example);
        }

        List<Map> maps = Lists.newArrayList();
        list.forEach(e -> {
            Map map = Maps.newHashMap();
            map.put("id", e.getId());
            map.put("name", e.getName());
            map.put("email", e.getEmail());
            map.put("account", e.getAccount());
            maps.add(map);
        });
        return list;
    }

    public void updateUserLoginInfo(HttpServletRequest request, long userId) {

        // 更新登陆信息
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setLoginDate(new Date());
        sysUser.setLoginIp(request.getRemoteAddr());
        cSysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void disable(Long userId, Integer disable) throws ServiceException {
        SysUser user = cSysUserMapper.selectByPrimaryKey(userId);
        if (null == user) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "未找到用户");
        }
        if (USER_TYPE.SYS.getType() == user.getUserType()) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "无法禁用系统用户");
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setUpdateBy(UserInfoHolder.getUserName());
        sysUser.setDisabled(disable >= 1);
        cSysUserMapper.updateByPrimaryKeySelective(sysUser);
        UserInfoHolder.getUserId();
    }

    @Override
    public List<Map> userPostProcessor(List<SysUser> users) {
        users.stream().forEach(e -> {
            e.setPassword(null);
        });
        List<Long> avatarIds = users.stream().map(e -> e.getAvatarId()).collect(Collectors.toList());
        List<SysUserAvatar> avatars = sysUserAvatarService.getByIds(avatarIds);
        List<Map> newUserList = users.stream().map(e -> {
            Map map = (Map) JSONObject.toJSON(e);
            Iterator<SysUserAvatar> iterator = avatars.iterator();
            while (iterator.hasNext()) {
                SysUserAvatar item = iterator.next();
                if (e.getAvatarId() == item.getId()) {
                    map.put("avatar", item.getUserAvatar());
                    iterator.remove();
                    break;
                }
            }
            return map;
        }).collect(Collectors.toList());
        return newUserList;
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long uid) {
        return csysUserRoleMapper.getRoleIdsByUid(uid);
    }

    @Override
    public List<SysRole> getRoleByUserId(Long uid) {
        List<Long> ids = this.getRoleIdsByUserId(uid);
        if (CollectionUtils.isNotEmpty(ids)) {
            return Lists.newArrayList();
        }
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(ids);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        return sysRoles;
    }
}
