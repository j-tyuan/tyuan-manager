package com.tyuan.manager.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.dao.base.customize.CSysUserMapper;
import com.tyuan.manager.base.service.SysPermissionService;
import com.tyuan.manager.base.service.SysRoleService;
import com.tyuan.manager.base.service.SysUserService;
import com.tyuan.manager.base.utils.DateUtil;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.pojo.SysRole;
import com.tyuan.model.base.pojo.SysUser;
import com.tyuan.model.base.pojo.SysUserExample;
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

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private CSysUserMapper cSysUserMapper;

    @Resource
    private SysRoleService sysRoleService;

    private SysPermissionService sysPermissionService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo getByParams(SysUserTableParamsVo param) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        String like = "%{0}%";
        if (StringUtils.isNotBlank(param.getName())) {
            criteria.andNameLike(MessageFormat.format(like, param.getName()));
        }
        if (StringUtils.isNotBlank(param.getAccount())) {
            criteria.andAccountEqualTo(param.getAccount());
        }
        if (StringUtils.isNotBlank(param.getPhone())) {
            criteria.andPhoneEqualTo(param.getPhone());
        }
        Date loginDate = DateUtil.format(param.getLoginDate());
        if (null != param.getLoginDate()) {
            criteria.andLoginDateGreaterThanOrEqualTo(loginDate);
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

        sysUser.setCreateBy(UserInfoHolder.getUserName());
        sysUser.setUpdateBy(UserInfoHolder.getUserName());
        String pass = sysUser.getPassword();
        if (StringUtils.isBlank(pass)) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "密码不许为空");
        }

        SysUserExample example = new SysUserExample();
        example.createCriteria().andNoEqualTo(sysUser.getNo());
        example.or().andAccountEqualTo(sysUser.getAccount());
        List<SysUser> list = cSysUserMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "员工编号或账号重复");
        }
        example.clear();
        example.createCriteria().andAccountEqualTo(sysUser.getAccount());


        pass = DigestUtils.md5DigestAsHex(pass.getBytes());
        sysUser.setPassword(pass);

        // 只允许创建普通用户
        sysUser.setUserType(USER_TYPE.ORDINARY.getType());
        cSysUserMapper.insertSelective(sysUser);
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
        sysUser.setAccount(null);
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
