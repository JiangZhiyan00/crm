package com.jiangzhiyan.crm.service;

import com.github.pagehelper.util.StringUtil;
import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerServeMapper;
import com.jiangzhiyan.crm.enums.ServeState;
import com.jiangzhiyan.crm.exceptions.ParamsException;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.utils.CookieUtil;
import com.jiangzhiyan.crm.vo.CustomerServe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author JiangZhiyan
 */
@Service
public class CustomerServeService extends BaseService<CustomerServe,Integer> {

    @Resource
    private CustomerServeMapper customerServeMapper;

    @Resource
    private HttpServletRequest request;

    /**
     * 添加服务
     * @param customerServe 待添加的服务
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCustomerServe(CustomerServe customerServe) {
        checkAddParams(customerServe);
        initAddParams(customerServe);
        AssertUtil.isTrue(customerServeMapper.insertSelective(customerServe) != 1,"服务器异常!服务添加失败...");
    }

    /**
     * 更新服务
     * @param customerServe 待更新的服务
     * @return 服务更新后提示的信息
     */
    @Transactional(rollbackFor = Exception.class)
    public String updateCustomerServe(CustomerServe customerServe) {
        String message = checkUpdateParams(customerServe);
        initUpdateParams(customerServe);
        customerServeMapper.updateByPrimaryKeySelective(customerServe);
        return message+"成功!";
    }

    /**
     * 批量删除服务
     * @param ids 待删除的服务id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteCustomerServes(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() < 1,"无效的请求!请重试...");
        AssertUtil.isTrue(customerServeMapper.deleteBatch(ids) != ids.size(),"服务器异常!删除失败...");
    }
//----------------------------------------分隔线---------------------------------------------------------

    /**
     * 添加服务参数校验
     * @param customerServe 待添加的服务
     */
    private void checkAddParams(CustomerServe customerServe) {
        AssertUtil.isTrue(customerServe == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServeType()),"服务类型不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCusName()),"客户名不能为空!");
        AssertUtil.isTrue(!ServeState.CREATE.getState().equals(customerServe.getState()),"服务状态不正确!");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceRequest()),"服务内容不能为空!");
    }

    /**
     * 添加服务参数初始化
     * @param customerServe 待添加的服务
     */
    private void initAddParams(CustomerServe customerServe) {
        CookieUtil.getCookieValue(request,"trueName");
        customerServe.setCreatePeople(CookieUtil.getCookieValue(request,"trueName"));
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
    }

    /**
     * 更新服务参数校验
     * @param customerServe 待更新的服务
     * @return 执行的操作的名称
     */
    private String checkUpdateParams(CustomerServe customerServe){
        AssertUtil.isTrue(customerServe.getId() == null,"无效的请求!请重试");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getState()),"服务状态不能为空!");
        //服务创建页面的更新操作
        if (ServeState.CREATE.getState().equals(customerServe.getState())){
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServeType()),"服务类型不能为空!");
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCusName()),"客户名不能为空!");
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceRequest()),"服务内容不能为空!");
            return "服务更新";
        //服务分配页面
        }else if (ServeState.ASSIGN.getState().equals(customerServe.getState())){

            return ServeState.ASSIGN.getInfo();
        //服务处理页面
        }else if (ServeState.PROCEED.getState().equals(customerServe.getState())){

            return ServeState.PROCEED.getInfo();
        //服务反馈页面
        }else if (ServeState.FEEDBACK.getState().equals(customerServe.getState())){

            return ServeState.FEEDBACK.getInfo();
        //服务归档页面
        }else if (ServeState.ARCHIVE.getState().equals(customerServe.getState())){

            return ServeState.ARCHIVE.getInfo();
        }else {
            throw new ParamsException("服务状态参数异常!");
        }
    }

    /**
     * 更新服务参数初始化
     * @param customerServe 待更新的服务
     */
    private void initUpdateParams(CustomerServe customerServe){
        //服务分配
        if (ServeState.ASSIGN.getState().equals(customerServe.getState())){
            customerServe.setAssignTime(new Date());
        //服务处理
        }else if (ServeState.PROCEED.getState().equals(customerServe.getState())){
            customerServe.setServiceProceTime(new Date());
        }
        customerServe.setUpdateDate(new Date());
    }
}
