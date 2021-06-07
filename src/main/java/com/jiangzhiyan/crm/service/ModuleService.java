package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.ModuleMapper;
import com.jiangzhiyan.crm.model.ZTreeModel;
import com.jiangzhiyan.crm.vo.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
