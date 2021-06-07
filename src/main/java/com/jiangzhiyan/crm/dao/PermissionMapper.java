package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.Permission;

import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface PermissionMapper extends BaseMapper<Permission,Integer> {
    /**
     * 查询指定roleId对应的所有权限数据的数量
     * @param roleId
     * @return
     */
    Integer selectByRoleId(Integer roleId);

    /**
     * 删除指定roleId对应的所有权限数据
     * @return
     */
    Integer deleteByRoleId(Integer roleId);

    /**
     * 获取登录用户拥有的权限
     * @param userId
     * @return
     */
    List<Permission> getLoginUserPermission(Integer userId);
}