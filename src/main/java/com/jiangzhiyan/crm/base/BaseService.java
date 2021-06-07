package com.jiangzhiyan.crm.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T,ID> {

    @Autowired
    private BaseMapper<T,ID> baseMapper;

    /**
     * 添加功能
     * @param entity
     * @return 添加记录返回行数
     * @throws DataAccessException
     */
    public Integer insertSelective(T entity) throws DataAccessException{
        return baseMapper.insertSelective(entity);
    }

    /**
     * 添加功能
     * @param entity
     * @return 添加记录返回主键值
     * @throws DataAccessException
     */
    public Integer insertHasKey(T entity) throws DataAccessException {
        return baseMapper.insertHasKey(entity);
    }

    /**
     * 批量添加
     * @param entities
     * @return 批量添加返回行数
     * @throws DataAccessException
     */
    public Integer insertBatch(List<T> entities) throws DataAccessException {
        return baseMapper.insertBatch(entities);
    }

    /**
     * 通过主键查询
     * @param id
     * @return
     * @throws DataAccessException
     */
    public T selectByPrimaryKey(ID id) throws DataAccessException{
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新单条记录
     * @param entity
     * @return
     * @throws DataAccessException
     */
    public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException{
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 批量更新
     * @param entities
     * @return
     * @throws DataAccessException
     */
    public Integer updateBatch(List<T> entities) throws DataAccessException{
        return baseMapper.updateBatch(entities);
    }

    /**
     * 删除单条记录
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Integer deleteByPrimaryKey(ID id) throws DataAccessException{
        return baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws DataAccessException
     */
    public Integer deleteBatch(List<ID> ids) throws DataAccessException{
        return baseMapper.deleteBatch(ids);
    }

    /**
     * 多条件分页查询
     * @param baseQuery
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> selectByParams(BaseQuery baseQuery){
        PageHelper.startPage(baseQuery.getPage(),baseQuery.getLimit());
        List<T> list = baseMapper.selectByParams(baseQuery);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>(4);
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
}
