package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.PermissionMapper;
import com.jiangzhiyan.crm.vo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionService extends BaseService<Permission,Integer> {

    @Resource
    private PermissionMapper permissionMapper;
}
