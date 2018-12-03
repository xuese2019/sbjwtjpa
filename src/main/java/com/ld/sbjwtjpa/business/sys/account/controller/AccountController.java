package com.ld.sbjwtjpa.business.sys.account.controller;

import com.ld.sbjwtjpa.business.sys.account.model.AccountModel;
import com.ld.sbjwtjpa.business.sys.account.service.AccountService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

@Api(value = "账号管理接口", description = "账号管理接口")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private AccountService service;

    @ApiOperation(value = "新增实体", notes = "后台带有数据验证")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<AccountModel> save(@Valid @RequestBody AccountModel model,
                                             BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
        model.setPassword(md5Password);
        return service.save(model);
    }

    @ApiOperation(value = "根据id删除实体")
    @ApiImplicitParam(paramType = "path", name = "uuid", value = "需要删除的id", required = true, dataType = "String")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> deleteByUuid(@PathVariable("uuid") String uuid) {
        return service.deleteByUuid(uuid);
    }

    @ApiOperation(value = "根据id修改实体", notes = "后台带有数据验证")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateByUuid(@Valid @RequestBody AccountModel model,
                                                     BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateByUuid(model);
    }

    @ApiOperation(value = "根据id获取实体", notes = "优先利用id查找,id找不到将条件作为account查找")
    @ApiImplicitParam(paramType = "path", name = "uuid", value = "需要获取的id,当id查询无结果时将尝试account查询", required = true, dataType = "String")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.GET)
    public ResponseResult<AccountModel> findOne(@PathVariable("uuid") String uuid) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        ResponseResult<AccountModel> result = service.findOne(model);
        if (result.isSuccess())
            return result;
        else {
            model.setUuid(null);
            model.setAccount(uuid);
            return service.findOne(model);
        }
    }
}
