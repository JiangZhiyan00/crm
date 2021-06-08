package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.ModuleMapper;
import com.jiangzhiyan.crm.model.ZTreeModel;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;

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
        checkAddModuleParams(module);
        initAddModuleParams(module);
        Integer result = moduleMapper.insertSelective(module);
        AssertUtil.isTrue(result != 1,"服务器异常!添加失败...");
    }

    /**
     * 添加资源参数校验
     * @param module
     */
    private void checkAddModuleParams(Module module) {
        AssertUtil.isTrue(module == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"资源名称不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"权限码不能为空!");
        AssertUtil.isTrue(module.getGrade() == null,"资源级别不能为空!");
        List<Module> existModules = moduleMapper.queryAllModules();
        for (Module m: existModules){
            AssertUtil.isTrue(module.getModuleName().equals(m.getModuleName()),"该资源名称已存在!");
            AssertUtil.isTrue(module.getOptValue().equals(m.getOptValue()),"该权限码已存在!");
        }
    }

    /**
     * 添加资源参数初始化
     * @param module
     */
    private void initAddModuleParams(Module module) {
        if (StringUtils.isBlank(module.getUrl())){
            module.setUrl("#");
        }
        module.setIsValid(1);
        module.setCreateDate(new Date());
    }
}
