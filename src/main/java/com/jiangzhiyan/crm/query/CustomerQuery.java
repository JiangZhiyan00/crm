package com.jiangzhiyan.crm.query;

import com.jiangzhiyan.crm.base.BaseQuery;

/**
 * @author JiangZhiyan
 */
public class CustomerQuery extends BaseQuery {

    private String customerId;

    private String name;

    private String level;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
