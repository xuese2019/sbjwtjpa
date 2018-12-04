package com.ld.sbjwtjpa.business.sys.rolesSubsidiary.controller;

import com.ld.sbjwtjpa.business.sys.account.service.AccountService;
import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.model.RolesSubsidiaryModel;
import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.service.RolesSubsidiaryService;
import com.ld.sbjwtjpa.sys.shiro.JWTUtils;
import com.ld.sbjwtjpa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(value = "赋予取消权限接口", description = "赋予取消权限接口")
@RestController
@RequestMapping("/roles")
public class RolesSubsidiaryController {

    @Autowired
    private RolesSubsidiaryService service;
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "赋予权限")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public ResponseResult<RolesSubsidiaryModel> save(@Valid @RequestBody RolesSubsidiaryModel model,
                                                     BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.save(model);
    }

    @ApiOperation(value = "根据id删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "uuid", value = "需要删除的id", required = true, dataType = "String"),
    })
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/roles/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<RolesSubsidiaryModel> delete(@PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @ApiOperation(value = "根据条件查询")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/roles/findAll", method = RequestMethod.GET)
    public ResponseResult<List<RolesSubsidiaryModel>> findAll(HttpServletRequest request) {
        String token = JWTUtils.getOrgid(request);
        if (token == null)
            return new ResponseResult<>(false, "logout");
        RolesSubsidiaryModel model1 = new RolesSubsidiaryModel();
        model1.setOrgId(token);
        return service.findAll(model1);
    }
}
