package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.UserAndRole;

import java.util.Date;
import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface UserAndRoleMapper extends BaseMapper<UserAndRole,Integer> {
    /**
     * 查询指定用户的职位
     * @param userId
     * @return
     */
    List<UserAndRole> selectByUserId(Integer userId);

    /**
     * 查询指定用户id对应的职位数量
     * @param userId
     * @return
     */
    Integer countByUserId(Integer userId);

    /**
     * 删除指定userId的所有职位信息
     * @param userId
     * @return
     */
    Integer deleteByUserId(Integer userId);

    /**
     * 查询多个userId对应的职位总数量
     * @param ids
     * @return
     */
    Integer countByUserIds(List<Integer> ids);
}