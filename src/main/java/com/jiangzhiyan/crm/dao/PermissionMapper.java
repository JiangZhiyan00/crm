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
     * 通过moduleId查询权限
     * @param moduleId
     * @return
     */
    List<Permission> selectByModuleId(Integer moduleId);

    /**
     * 根据moduleId删除权限
     * @param moduleId
     * @return 数据变动数目
     */
    Integer deleteByModuleId(Integer moduleId);
}