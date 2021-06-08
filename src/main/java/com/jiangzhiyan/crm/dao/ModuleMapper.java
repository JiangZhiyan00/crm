package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.model.ZTreeModel;
import com.jiangzhiyan.crm.vo.Module;

import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface ModuleMapper extends BaseMapper<Module,Integer> {

    /**
     * 查询所有资源信息,返回z-tree插件要求数据格式
     * 如果参数roleId对应的权限存在,则节点被选中
     * @return json like [{id:2,pId:1,name:"moduleName",checked:true},...]
     */
    List<ZTreeModel> selectAllModules(Integer roleId);

    /**
     * 查询所有有效资源信息
     * @return List<Module>集合
     */
    List<Module> queryAllModules();
}