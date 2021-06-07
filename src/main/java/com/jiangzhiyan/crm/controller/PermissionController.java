package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;
}
