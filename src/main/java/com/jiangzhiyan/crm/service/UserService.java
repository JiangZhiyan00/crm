package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.UserAndRoleMapper;
import com.jiangzhiyan.crm.dao.UserMapper;
import com.jiangzhiyan.crm.model.UserModel;
import com.jiangzhiyan.crm.utils.*;
import com.jiangzhiyan.crm.vo.User;
import com.jiangzhiyan.crm.vo.UserAndRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserAndRoleMapper userAndRoleMapper;

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public UserModel userLogin(String userName,String password){

        //校验参数是否为空
        checkLoginParams(userName,password);
        //按userName查询数据库
        User user = userMapper.queryUserByName(userName);
        //查询结果为空,则抛出异常
        AssertUtil.isTrue(user == null,"该用户名不存在!");
        //比较用户输入密码与用户实际密码
        checkUserPwd(password,user.getUserPassword(),"密码错误!请重新输入!");
        //构建用户模型
        return buildUserInfo(user);
    }

    /**
     * 修改密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(HttpServletRequest request, String oldPassword,
                                      String newPassword, String confirmPassword){

        checkUpdatePwdParams(oldPassword,newPassword,confirmPassword);
        //从cookie中获取当前用户id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user == null,"修改密码失败,请重新登录后再尝试修改!");
        checkUserPwd(oldPassword,user.getUserPassword(),"原始密码错误!更改失败!");
        user.setUserPassword(Md5Util.encode(newPassword));
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"密码修改失败,请稍后重试!");
    }

    /**
     * 查询所有销售(有效数据)
     * @return
     */
    public List<Map<String,Object>> selectAllSales(){
        return userMapper.selectAllSales();
    }

    /**
     * 添加用户并设置职位
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) {
        checkAddOrUpdateUserParams(user);
        initAddOrUpdateUserParams(user);
        Integer insertUserResult = userMapper.insertHasKey(user);
        AssertUtil.isTrue(insertUserResult != 1,"服务器异常!添加失败...");
        List<UserAndRole> userAndRoleList = getAddOrUpdateUserAndRoleList(user);
        Integer result = userAndRoleMapper.insertBatch(userAndRoleList);
        AssertUtil.isTrue(result != userAndRoleList.size(),"服务器异常!添加失败...");
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        checkAddOrUpdateUserParams(user);
        initAddOrUpdateUserParams(user);
        List<UserAndRole> list = getAddOrUpdateUserAndRoleList(user);
        Integer updateUserResult = userMapper.updateByPrimaryKeySelective(user);
        AssertUtil.isTrue(updateUserResult != 1,"服务器异常!更新失败...");
        Integer roleCount = userAndRoleMapper.countByUserId(user.getId());
        Integer deleteUserAndRolesResult = userAndRoleMapper.deleteByUserId(user.getId());
        AssertUtil.isTrue(!roleCount.equals(deleteUserAndRolesResult),"服务器异常!更新失败...");
        Integer updateResult = userAndRoleMapper.insertBatch(list);
        AssertUtil.isTrue(updateResult != user.getRoleIds().size(),"服务器异常!更新失败...");
    }

    /**
     * 删除用户(单个/批量)
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUsersByIds(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() == 0,"请求信息为空,请重试...");
        Integer deleteUserResult = userMapper.deleteBatch(ids);
        AssertUtil.isTrue(deleteUserResult != ids.size(),"服务器异常!删除失败...");
        Integer count = userAndRoleMapper.countByUserIds(ids);
        Integer deleteUserAndRoleResult = userAndRoleMapper.deleteBatch(ids);
        AssertUtil.isTrue(!deleteUserAndRoleResult.equals(count),"服务器异常!删除失败...");
    }

    /**
     * 查询所有职位为客户经理的用户
     * @param serveId 客户服务记录id
     * @return xm-select格式:id,name,selected三个字段
     */
    public List<Map<String, Object>> selectAllCustomerManagersForXmSelect(Integer serveId) {
        return userMapper.selectAllCustomerManagersForXmSelect(serveId);
    }

    /**
     * 获取登录用户能够访问的资源列表
     * @param userId
     * @return
     */
    public List<String> getLoginUserModules(Integer userId) {
        return userMapper.getLoginUserModules(userId);
    }

    /**
     * 校验更改密码参数
     * @param oldPassword 用户输入的原始密码
     * @param newPassword 用户输入的新密码
     * @param confirmPassword 用户输入的确认密码
     */
    private void checkUpdatePwdParams(String oldPassword, String newPassword, String confirmPassword) {

        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原始密码不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"确认密码不能为空!");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"两次输入的新密码不一致!");
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码和原始密码不能一样!");
    }

    /**
     * 校验用户名密码是否为空
     * @param userName
     * @param password
     */
    private void checkLoginParams(String userName,String password){
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空!");
    }

    /**
     * 比较用户输入密码通过MD5加密后是否与正确密码一致
     * @param userPwd 用户输入密码
     * @param pwd 加密后的正确密码
     * @param msg 异常信息提示
     */
    private void checkUserPwd(String userPwd,String pwd,String msg){
        AssertUtil.isTrue(!Md5Util.encode(userPwd).equals(pwd),msg);
    }

    /**
     * 构建用户简单信息模型
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        //加密userId
        userModel.setUserIdStr(UserIdBase64.encoderUserId(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    /**
     * 添加用户参数校验
     * @param user
     */
    private void checkAddOrUpdateUserParams(User user) {
        AssertUtil.isTrue(user == null,"请求信息为空!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"真实姓名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()),"用户邮箱不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()),"手机号码不能为空!");
        AssertUtil.isTrue(user.getRoleIds() == null || user.getRoleIds().size() == 0,
                "职位不能为空!");
        AssertUtil.isTrue(!EmailUtil.isValidEmail(user.getEmail()),"邮箱格式不正确!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(user.getPhone()),"手机号码格式不正确!");
        User existUser = userMapper.queryUserByName(user.getUserName());
        if (user.getId() == null){
            AssertUtil.isTrue(existUser != null,"该用户名已存在!");
        }else {
            AssertUtil.isTrue(existUser != null && !existUser.getId().equals(user.getId()),
                    "该用户名已存在!");
        }
    }

    /**
     * 初始化新增/更新用户参数
     * @param user
     */
    private void initAddOrUpdateUserParams(User user){
        if (user.getId() == null){
            //新用户初始密码
            user.setUserPassword(Md5Util.encode("123456"));
            user.setCreateDate(new Date());
            user.setIsValid(1);
        }else {
            user.setUpdateDate(new Date());
        }
    }

    /**
     * 初始化添加/更新用户-职位关系参数
     * @param user
     * @return
     */
    private List<UserAndRole> getAddOrUpdateUserAndRoleList(User user){

        UserAndRole userAndRole = null;
        Date date = new Date();
        List<UserAndRole> userAndRoleList = new ArrayList<>();
        for (Integer roleId : user.getRoleIds()){
            userAndRole = new UserAndRole();
            userAndRole.setUserId(user.getId());
            userAndRole.setRoleId(roleId);
            userAndRole.setCreateDate(date);
            userAndRole.setUpdateDate(date);
            userAndRoleList.add(userAndRole);
        }
        return userAndRoleList;
    }
}
