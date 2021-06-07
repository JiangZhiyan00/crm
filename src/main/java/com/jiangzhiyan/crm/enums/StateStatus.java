package com.jiangzhiyan.crm.enums;

/**
 * @author JiangZhiyan
 */
public enum StateStatus {

    //营销机会分配状态
    UN_STATED(0,"未分配"),
    STATED(1,"已分配");

    private final Integer status;
    private final String statusMessage;

    StateStatus(Integer status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}


