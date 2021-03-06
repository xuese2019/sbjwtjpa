package com.ld.sbjwtjpa.business.sys.jurisdiction.controller;

import com.ld.sbjwtjpa.business.sys.jurisdiction.model.JurisdictionModel;
import com.ld.sbjwtjpa.business.sys.jurisdiction.service.JurisdictionService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "权限基础接口", description = "权限基础接口")
@RestController
@RequestMapping("/jurisdiction")
public class JurisdictionController {

    @Autowired
    private JurisdictionService service;

    @ApiOperation(value = "获取所有的权限基础信息")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionModel>> findAll() {
        return service.findAll(null);
    }

    @ApiOperation(value = "获取所有的左侧菜单信息")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/organization/menu", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionModel>> findAllMenus() {
        JurisdictionModel model = new JurisdictionModel();
        model.setJurType(1);
        return service.findAll(model);
    }

    @ApiOperation(value = "新增权限（菜单）信息")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    public ResponseResult<JurisdictionModel> findAll(@RequestBody JurisdictionModel model) {
        return service.save(model);
    }
}
