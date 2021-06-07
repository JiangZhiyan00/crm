package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.ModuleMapper;
import com.jiangzhiyan.crm.dao.PermissionMapper;
import com.jiangzhiyan.crm.dao.RoleMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.Permission;
import com.jiangzhiyan.crm.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private ModuleMapper moduleMapper;

    /**
     * 查询所有职位
     */
    public List<Map<String,Object>> selectAllRoles(){
        return roleMapper.selectAllRoles();
    }

    /**
     * 通过userId查询对应的所有职位
     * @param userId
     * @return
     */
    public List<Map<String,Object>> selectRolesByUserId(Integer userId){
        AssertUtil.isTrue(userId == null,"无效的请求!");
        return roleMapper.selectAllRolesByUserId(userId);
    }

    /**
     * 添加职位信息
     * @param role
     */
    @Transactional(rollbackFor = Exception.class)
    public void addRole(Role role){
        checkAddOrUpdateRoleParams(role);
        initAddOrUpdateRoleParams(role);
        Integer result = roleMapper.insertSelective(role);
        AssertUtil.isTrue(result != 1,"服务器异常!职位信息添加失败...");
    }

    /**
     * 更新职位信息
     * @param role
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        checkAddOrUpdateRoleParams(role);
        initAddOrUpdateRoleParams(role);
        Integer result = roleMapper.updateByPrimaryKeySelective(role);
        AssertUtil.isTrue(result != 1,"服务器异常!职位信息更新失败...");
    }

    /**
     * 删除职位信息(实际上是更新,状态改为无效)
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(List<Integer> ids){
        AssertUtil.isTrue(ids == null || ids.size() == 0 ,"请求信息为空!请重试...");
        Integer result = roleMapper.deleteBatch(ids);
        AssertUtil.isTrue(!result.equals(ids.size()),"服务器异常!职位信息删除失败...");
    }

    /**
     * 职位授权
     * @param moduleIds
     * @param roleId
     */
    @Transactional(rollbackFor = Exception.class)
    public void addGrant(List<Integer> moduleIds, Integer roleId) {
        AssertUtil.isTrue(roleId == null || moduleIds == null || moduleIds.size() == 0,
                "请求信息为空!请重试...");
        Integer count = permissionMapper.selectByRoleId(roleId);
        if (count > 0){
            Integer deleteResult = permissionMapper.deleteByRoleId(roleId);
            AssertUtil.isTrue(!count.equals(deleteResult),"服务器异常!授权失败...");
        }
        List<Permission> list = initAddGrantParams(moduleIds,roleId);
        Integer insertResult = permissionMapper.insertBatch(list);
        AssertUtil.isTrue(!insertResult.equals(moduleIds.size()),"服务器异常!授权失败...");
    }

    /**
     * 添加/更新职位信息参数检查
     * @param role
     */
    private void checkAddOrUpdateRoleParams(Role role) {
        AssertUtil.isTrue(role == null,"请求信息为空!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"职位名称不能为空!");
        Role existRole = roleMapper.selectRoleByRoleName(role.getRoleName());
        if (role.getId() == null){
            AssertUtil.isTrue(existRole != null,"该职位名称已存在!请重试!");
        }else {
            AssertUtil.isTrue(existRole != null && !existRole.getId().equals(role.getId()),"该职位名称已存在!请重试!");
        }
    }

    /**
     * 添加/更新职位信息参数初始化
     * @param role
     */
    private void initAddOrUpdateRoleParams(Role role){
        if (role.getId() == null){
            role.setIsValid(1);
            role.setCreateDate(new Date());
        }else {
            role.setUpdateDate(new Date());
        }
    }

    /**
     * 授权参数初始化
     * @param moduleIds
     * @param roleId
     * @return
     */
    private List<Permission> initAddGrantParams(List<Integer> moduleIds,Integer roleId){
        List<Permission> permissionList = new ArrayList<>();
        Permission permission;
        Date date = new Date();
        for (Integer moduleId : moduleIds){
            permission = new Permission();
            permission.setRoleId(roleId);
            permission.setCreateDate(date);
            permission.setUpdateDate(date);
            permission.setModuleId(moduleId);
            //授权码赋值
            permission.setAclValue(moduleMapper.selectByPrimaryKey(moduleId).getOptValue());
            permissionList.add(permission);
        }
        return permissionList;
    }
}
