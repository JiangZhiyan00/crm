package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.Role;

import java.util.List;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
public interface RoleMapper extends BaseMapper<Role,Integer> {
    /**
     * 查询所有职位
     */
    List<Map<String,Object>> selectAllRoles();

    /**
     * 通过uerId查询所有职位,userId对应的职位被标记为选中
     */
    List<Map<String,Object>> selectAllRolesByUserId(Integer userId);

    /**
     * 按职位名称查询职位信息
     * @param roleName
     * @return
     */
    Role selectRoleByRoleName(String roleName);
}