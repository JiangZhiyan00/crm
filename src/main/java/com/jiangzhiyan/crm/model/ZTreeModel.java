package com.jiangzhiyan.crm.model;

public class ZTreeModel {

    /**
     * 资源模型类,z-tree插件要求字段名是id,pId,name
     */
    private Integer id;
    private Integer pId;
    private String name;
    /**
     * 初始是否被选中
     * */
    private boolean checked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
