package com.jiangzhiyan.crm.query;

import com.jiangzhiyan.crm.base.BaseQuery;

public class CustomerServeQuery extends BaseQuery {

    private String cusName;

    private String serveType;

    private String state;

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getServeType() {
        return serveType;
    }

    public void setServeType(String serveType) {
        this.serveType = serveType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
