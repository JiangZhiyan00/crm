package com.jiangzhiyan.crm.query;

import com.jiangzhiyan.crm.base.BaseQuery;

/**
 * @author JiangZhiyan
 */
public class CustomerOrderQuery extends BaseQuery {

    private Integer cusId;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}
