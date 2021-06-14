package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.CustomerReprieve;

import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface CustomerReprieveMapper extends BaseMapper<CustomerReprieve,Integer> {

    List<CustomerReprieve> selectByLossId(Integer lossId);

    Integer selectCountByLossId(Integer lossId);

    Integer cancelReprieveByLossId(Integer lossId);

}