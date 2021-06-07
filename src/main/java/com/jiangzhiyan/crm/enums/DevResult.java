package com.jiangzhiyan.crm.enums;

/**
 * @author JiangZhiyan
 */
public enum DevResult {

    //开发状态
    UN_DEV(0,"未开发"),
    IN_DEV(1,"开发中"),
    DEV_SUCCESS(2,"开发成功"),
    DEV_FAILED(3,"开发失败");

    private final Integer devResult;

    private final String devResultMessage;

    DevResult(Integer devResult, String devResultMessage) {
        this.devResult = devResult;
        this.devResultMessage = devResultMessage;
    }

    public Integer getDevResult() {
        return devResult;
    }

    public String getDevResultMessage() {
        return devResultMessage;
    }
}

