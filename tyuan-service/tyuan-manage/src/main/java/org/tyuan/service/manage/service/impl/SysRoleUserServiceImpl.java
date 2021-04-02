/**
 * @ClassName RoleServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:58
 */
package org.tyuan.service.manage.service.impl;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.dao.customize.CSysUserRoleMapper;
import org.tyuan.service.manage.cache.UserInfoCacheService;
import org.tyuan.service.manage.service.SysRoleUserService;
import org.tyuan.service.manage.service.SysUserService;
import org.tyuan.service.model.pojo.SysUserExample;
import org.tyuan.service.model.pojo.SysUserRole;
import org.tyuan.service.model.pojo.SysUserRoleExample;
import org.tyuan.service.model.vo.sys.RoleUserTableParamsVo;
import org.tyuan.service.model.vo.sys.SysRoleUserVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Resource
    CSysUserRoleMapper csysUserRoleMapper;

    @Resource
    UserInfoCacheService userInfoCacheService;

    @Resource
    SysUserService sysUserService;


    @Override
    public PageInfo getUser(RoleUserTableParamsVo paramsVo) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(paramsVo.getRoleId());
        List<SysUserRole> roles = csysUserRoleMapper.selectByExample(example);
        List<Long> longs = roles.stream().map(e -> e.getUserId()).collect(Collectors.toList());

        SysUserExample sysUserExample = sysUserService.getUserExampleByParams(paramsVo);
        List<SysUserExample.Criteria> criterias = sysUserExample.getOredCriteria();
        SysUserExample.Criteria criteria = criterias.get(0);
        if (paramsVo.getSearchType() == 1) {
            // 如果没有用户绑定此角色，直接返回空pageInfo对象
            if (CollectionUtils.isNotEmpty(longs)) {
                criteria.andIdIn(longs);
            } else {
                return new PageInfo();
            }
        } else if (CollectionUtils.isNotEmpty(longs) && paramsVo.getSearchType() == 2) {
            criteria.andIdNotIn(longs);
        }
        return sysUserService.getByExample(sysUserExample, paramsVo);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void bindUser(SysRoleUserVo sysRoleUserVo) {
        csysUserRoleMapper.batchInsert(sysRoleUserVo.getRoleId(), sysRoleUserVo.getUserIds());
        for (Long l : sysRoleUserVo.getUserIds()) {
            userInfoCacheService.leaveMessage(l, "你的权限发生改变，请重新登陆");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void unbindUser(SysRoleUserVo sysRoleUserVo) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(sysRoleUserVo.getRoleId()).andUserIdIn(sysRoleUserVo.getUserIds());
        csysUserRoleMapper.deleteByExample(example);
        for (Long l : sysRoleUserVo.getUserIds()) {
            userInfoCacheService.leaveMessage(l, "你的权限发生改变，请重新登陆");
        }
    }
}
