package com.jiangzhiyan.crm.base;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMapper<T,ID> {

    /**
     * 添加功能
     * @param entity
     * @return 添加记录返回行数
     * @throws DataAccessException
     */
    Integer insertSelective(T entity) throws DataAccessException;

    /**
     * 添加功能
     * @param entity
     * @return 添加记录返回主键值
     * @throws DataAccessException
     */
    Integer insertHasKey(T entity) throws DataAccessException;

    /**
     * 批量添加
     * @param entities
     * @return 批量添加返回行数
     * @throws DataAccessException
     */
    Integer insertBatch(List<T> entities) throws DataAccessException;

    /**
     * 通过主键查询
     * @param id
     * @return
     * @throws DataAccessException
     */
    T selectByPrimaryKey(ID id) throws DataAccessException;

    /**
     * 多条件查询
     * @param baseQuery
     * @return
     * @throws DataAccessException
     */
    List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;

    /**
     * 更新单条记录
     * @param entity
     * @return
     * @throws DataAccessException
     */
    Integer updateByPrimaryKeySelective(T entity) throws DataAccessException;

    /**
     * 批量更新
     * @param entities
     * @return
     * @throws DataAccessException
     */
    Integer updateBatch(List<T> entities) throws DataAccessException;

    /**
     * 删除单条记录
     * @param id
     * @return
     * @throws DataAccessException
     */
    Integer deleteByPrimaryKey(ID id) throws DataAccessException;

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws DataAccessException
     */
    Integer deleteBatch(List<ID> ids) throws DataAccessException;

}
