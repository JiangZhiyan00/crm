package com.jiangzhiyan.crm.enums;

/**
 * @author JiangZhiyan
 */
public enum ServeState {

    //服务状态
    CREATE("fw_001","服务创建"),
    ASSIGN("fw_002","服务分配"),
    PROCEED("fw_003","服务处理"),
    FEEDBACK("fw_004","服务反馈"),
    ARCHIVE("fw_005","服务归档");

    private String state;

    private String info;

    ServeState(String state, String info) {
        this.state = state;
        this.info = info;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
