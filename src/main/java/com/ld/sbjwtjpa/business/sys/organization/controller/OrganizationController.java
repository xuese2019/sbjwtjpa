package com.ld.sbjwtjpa.business.sys.organization.controller;

import com.ld.sbjwtjpa.business.sys.organization.model.OrganizationModel;
import com.ld.sbjwtjpa.business.sys.organization.service.OrganizationService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "组织机构接口", description = "组织机构接口")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private OrganizationService service;

    @ApiOperation(value = "新增组织机构", notes = "后台带有数据验证")
    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    public ResponseResult<OrganizationModel> save(@Valid @RequestBody OrganizationModel model,
                                                  BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.save(model);
    }

    @ApiOperation(value = "根据id删除组织机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "uuid", value = "需要删除的组织id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/organization/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<OrganizationModel> delete(@PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @ApiOperation(value = "根据id修改组织机构")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "uuid", value = "需要修改的组织id", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/organization/{uuid}", method = RequestMethod.PUT)
    public ResponseResult<OrganizationModel> update(@PathVariable("uuid") String uuid,
                                                    @Valid @RequestBody OrganizationModel model,
                                                    BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.update(model);
    }

    @ApiOperation(value = "获取所有的组织")
    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public ResponseResult<List<OrganizationModel>> findAll() {
        return service.findRecursionAll();
    }
}
