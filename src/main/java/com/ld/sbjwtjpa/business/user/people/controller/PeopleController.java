package com.ld.sbjwtjpa.business.user.people.controller;

import com.ld.sbjwtjpa.business.user.people.model.PeopleModel;
import com.ld.sbjwtjpa.business.user.people.serivice.PeopleService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "员工详细信息接口", description = "员工详细信息接口")
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private PeopleService service;

    @ApiOperation(value = "新增实体", notes = "权限标记 people:save")
    @RequiresPermissions(value = {"people:save"})
    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseResult<PeopleModel> save(@Valid @RequestBody PeopleModel model,
                                            BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.save(model);
    }

    @ApiOperation(value = "根据id删除实体", notes = "权限标记 people:delete")
    @RequiresPermissions(value = {"people:delete"})
    @RequestMapping(value = "/people/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<PeopleModel> deleteByUuid(@PathVariable("uuid") String uuid) {
        return service.deleteByUuid(uuid);
    }

    @ApiOperation(value = "根据id修改实体", notes = "权限标记 people:update")
    @RequiresPermissions(value = {"people:update"})
    @RequestMapping(value = "/people", method = RequestMethod.PUT)
    public ResponseResult<PeopleModel> updateByUuid(@Valid @RequestBody PeopleModel model,
                                                    BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateByUuid(model);
    }

    @ApiOperation(value = "根据账户id修改实体，主要用户个人资料的修改", notes = "无权限标记")
    @RequestMapping(value = "/people/accid", method = RequestMethod.PUT)
    public ResponseResult<PeopleModel> updateByAccId(@Valid @RequestBody PeopleModel model,
                                                     BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateByAccId(model);
    }

    @ApiOperation(value = "根据id获取实体", notes = "权限标记 people:query")
    @RequiresPermissions(value = {"people:query"})
    @RequestMapping(value = "/people/{uuid}", method = RequestMethod.GET)
    public ResponseResult<PeopleModel> findOne(@PathVariable("uuid") String uuid) {
        PeopleModel model = new PeopleModel();
        model.setUuid(uuid);
        return service.findOne(model);
    }

    @ApiOperation(value = "根据账户id获取实体,主要用户个人资料", notes = "无权限标记")
    @RequestMapping(value = "/people/accid/{uuid}", method = RequestMethod.GET)
    public ResponseResult<PeopleModel> findOne2(@PathVariable("uuid") String uuid) {
        PeopleModel model = new PeopleModel();
        model.setAccId(uuid);
        return service.findOne(model);
    }

    @ApiOperation(value = "分页条件查询", notes = "权限标记 people:page")
    @RequiresPermissions(value = {"people:page"})
    @RequestMapping(value = "/people/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<Page<PeopleModel>> findAllPage(@PathVariable("pageNow") int pageNow,
                                                         @RequestBody PeopleModel model) {
        return service.findAllPage(pageNow, 3, model);
    }

}
