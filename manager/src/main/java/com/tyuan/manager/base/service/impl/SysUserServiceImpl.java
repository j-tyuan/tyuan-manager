package com.tyuan.manager.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.common.utils.TreeUtils;
import com.tyuan.dao.base.customize.CSysUserMapper;
import com.tyuan.dao.base.mapper.OrganizationInstitutionMapper;
import com.tyuan.manager.base.cache.LocalCache;
import com.tyuan.manager.base.service.SysPermissionService;
import com.tyuan.manager.base.service.SysRoleService;
import com.tyuan.manager.base.service.SysUserService;
import com.tyuan.manager.base.utils.DateUtil;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.pojo.*;
import com.tyuan.model.base.pojo.custom.COrganizationInstitution;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.SysUserTableParamsVo;
import com.tyuan.model.base.vo.sys.UserAuthVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private CSysUserMapper cSysUserMapper;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    OrganizationInstitutionMapper organizationInstitutionMapper;

    @Override
    public PageInfo getByParams(SysUserTableParamsVo param) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        boolean flag = false;
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

        PageHelper.offsetPage(param.getOffset(), param.getPageSize()).setOrderBy("update_date desc");
        List<SysUser> result = cSysUserMapper.selectByExample(example);
        result.stream().forEach(e -> {
            e.setPassword(null);
        });
        return new PageInfo<>(result);
    }

    @Override
    public List<SysUser> getAll(String name, String phone) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        String like = "%{0}%";
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(MessageFormat.format(like, name));
        }
        if (StringUtils.isNotBlank(phone)) {
            criteria.andNameLike(MessageFormat.format(like, phone));
        }
        //只允许查看普通用户
        criteria.andUserTypeNotEqualTo(USER_TYPE.SYS.getType());
        List<SysUser> result = cSysUserMapper.selectByExample(example);
        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysUser sysUser) throws ServiceException {
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
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysUser sysUser) throws ServiceException {

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

        //只允许修改普通用户
        sysUser.setUserType(USER_TYPE.ORDINARY.getType());
        cSysUserMapper.updateByPrimaryKeySelective(sysUser);
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
    public void editAuth(Long userId, UserAuthVo vo) {
        List<Long> roleIds = vo.getRoleIds();

        // 防止当前用户添加不属于他自己角色的角色
        Iterator<Long> iterator = roleIds.iterator();
        while (iterator.hasNext()) {
            Long roleId = iterator.next();
            if (!sysRoleService.hasRoleById(roleId)) {
                iterator.remove();
            }
        }

        Long id = userId;
        // 防止当前用户删除不属于他自己角色的角色
        List<SysRole> list = sysRoleService.getRoleByUserId(id);
        Iterator<SysRole> sysRoleIterator = list.iterator();
        while (sysRoleIterator.hasNext()) {
            SysRole item = sysRoleIterator.next();
            if (!sysRoleService.hasRoleById(item.getId())) {
                roleIds.add(item.getId());
            }
        }
        cSysUserMapper.unBindByUserId(id);
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        cSysUserMapper.bindRole(id, roleIds);
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

}
