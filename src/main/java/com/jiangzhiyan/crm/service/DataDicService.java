package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.DataDicMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.DataDic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author JiangZhiyan
 */
@Service
public class DataDicService extends BaseService<DataDic,Integer> {

    @Resource
    private DataDicMapper dataDicMapper;

    /**
     * 添加字典数据
     * @param dataDic 待添加的字典数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDataDic(DataDic dataDic) {
        checkParams(dataDic);
        initParams(dataDic);
        AssertUtil.isTrue(dataDicMapper.insertSelective(dataDic) != 1,"服务器异常!添加失败...");
    }

    /**
     * 更新字典数据
     * @param dataDic 待更新的字典数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDataDic(DataDic dataDic) {
        checkParams(dataDic);
        initParams(dataDic);
        AssertUtil.isTrue(dataDicMapper.updateByPrimaryKeySelective(dataDic) != 1,"服务器异常!更新失败...");
    }

    /**
     * 删除字典数据(批量)
     * @param ids 待删除的字典数据id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteDataDic(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() < 1,"无效的请求!请重试...");
        AssertUtil.isTrue(dataDicMapper.deleteBatch(ids) != ids.size(),"服务器异常!删除失败...");
    }

//--------------------------------------------分隔线---------------------------------------------------

    /**
     * 添加/更新字典数据参数校验
     */
    private void checkParams(DataDic dataDic) {
        AssertUtil.isTrue(dataDic == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(dataDic.getDataDicName()),"字典数据名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(dataDic.getDataDicValue()),"字典数据值不能为空!");
        if (dataDic.getId() != null){
            AssertUtil.isTrue(dataDicMapper.selectByPrimaryKey(dataDic.getId()) == null,"该字典数据不存在!");
        }
    }

    /**
     * 添加/更新字典数据参数初始化
     */
    private void initParams(DataDic dataDic) {
        dataDic.setIsValid((byte) 1);
        if (dataDic.getId() == null){
            dataDic.setCreateDate(new Date());
        }else {
            dataDic.setUpdateDate(new Date());
        }
    }
}
