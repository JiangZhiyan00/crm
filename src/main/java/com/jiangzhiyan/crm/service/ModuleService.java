package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.ModuleMapper;
import com.jiangzhiyan.crm.dao.PermissionMapper;
import com.jiangzhiyan.crm.model.ZTreeModel;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.Module;
import com.jiangzhiyan.crm.vo.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 查询所有资源信息,返回z-tree插件要求数据格式
     * 如果参数roleId对应的权限存在,则节点被选中
     * @return json like [{id:2,pId:1,name:"moduleName",checked:true},...]
     */
    public List<ZTreeModel> selectAllModules(Integer roleId){
        return moduleMapper.selectAllModules(roleId);
    }

    /**
     * 查询所有有效资源
     * @return layui数据表要求格式的Map
     */
    public Map<String, Object> queryAllModules() {
        Map<String,Object> map = new HashMap<>(4);
        List<Module> modules = moduleMapper.queryAllModules();
        map.put("code",0);
        map.put("msg","success");
        map.put("count",modules.size());
        map.put("data",modules);
        return map;
    }

    /**
     * 添加资源
     * @param module
     */
    @Transactional(rollbackFor = Exception.class)
    public void addModule(Module module) {
        checkAddOrUpdateModuleParams(module);
        initAddOrUpdateModuleParams(module);
        Integer result = moduleMapper.insertSelective(module);
        AssertUtil.isTrue(result != 1,"服务器异常!添加失败...");
    }

    /**
     * 更新资源
     * @param module
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateModule(Module module) {
        checkAddOrUpdateModuleParams(module);
        initAddOrUpdateModuleParams(module);
        Integer result = moduleMapper.updateByPrimaryKeySelective(module);
        AssertUtil.isTrue(result != 1,"服务器异常!更新失败...");
    }

    /**
     * 删除指定资源(实际是更新操作)
     * @param id 待删除的资源id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteModule(Integer id) {
        AssertUtil.isTrue(id == null,"无效的请求!请重试...");
        AssertUtil.isTrue(moduleMapper.selectByPrimaryKey(id) == null,"不存在此资源!");
        List<Module> modules = moduleMapper.selectByParentId(id);
        AssertUtil.isTrue(modules != null && modules.size() != 0,"存在子级资源!无法删除...");
        List<Permission> permissions = permissionMapper.selectByModuleId(id);
        if (permissions != null && permissions.size() != 0){
            Integer deletePermissionResult = permissionMapper.deleteByModuleId(id);
            AssertUtil.isTrue(permissions.size() != deletePermissionResult,"服务器异常!删除失败...");
        }
        Integer deleteModuleResult = moduleMapper.deleteModule(id);
        AssertUtil.isTrue(deleteModuleResult != 1,"服务器异常!删除失败...");
    }

    /**
     * 添加/更新资源参数校验
     * @param module
     */
    private void checkAddOrUpdateModuleParams(Module module) {
        AssertUtil.isTrue(module == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"资源名称不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"权限码不能为空!");
        AssertUtil.isTrue(module.getGrade() == null,"资源级别不能为空!");
        if (module.getGrade() == 1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"菜单地址不能为空!");
        }
        List<Module> existModules = moduleMapper.queryAllModules();
        //添加操作
        if (module.getId() == null){
            for (Module m: existModules){
                AssertUtil.isTrue(module.getModuleName().equals(m.getModuleName()),"该资源名称已存在!");
                AssertUtil.isTrue(module.getOptValue().equals(m.getOptValue()),"该权限码已存在!");
                if (module.getGrade() == 1){
                    AssertUtil.isTrue(module.getUrl().equals(m.getUrl()),"该菜单地址已存在!");
                }
            }
        //更新操作
        }else {
            for (Module m: existModules){
                AssertUtil.isTrue(module.getModuleName().equals(m.getModuleName()) &&
                        !module.getId().equals(m.getId()),"该资源名称已存在!");
                AssertUtil.isTrue(module.getOptValue().equals(m.getOptValue()) &&
                        !module.getId().equals(m.getId()),"该权限码已存在!");
                if (module.getGrade() == 1){
                    AssertUtil.isTrue(module.getUrl().equals(m.getUrl()) &&
                            !module.getId().equals(m.getId()),"该菜单地址已存在!");
                }
            }
        }
    }

    /**
     * 添加/更新资源参数初始化
     * @param module
     */
    private void initAddOrUpdateModuleParams(Module module) {
        if (StringUtils.isBlank(module.getUrl())){
            module.setUrl("#");
        }
        if (module.getId() == null){
            module.setIsValid(1);
            module.setCreateDate(new Date());
        }else {
            module.setUpdateDate(new Date());
        }
    }
}
