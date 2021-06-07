package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.query.UserQuery;
import com.jiangzhiyan.crm.vo.User;
import com.jiangzhiyan.crm.vo.UserAndRole;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    User queryUserByName(String userName) throws DataAccessException;

    /**
     * 查询所有销售
     * @return
     */
    List<Map<String,Object>> selectAllSales() throws DataAccessException;

    /**
     * 获取登录用户能够访问的资源列表
     * @param userId
     * @return
     */
    List<String> getLoginUserModules(Integer userId);
}