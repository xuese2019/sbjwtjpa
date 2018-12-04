package com.ld.sbjwtjpa.sys.shiro;

import com.auth0.jwt.interfaces.Claim;
import com.ld.sbjwtjpa.business.sys.account.model.AccountModel;
import com.ld.sbjwtjpa.business.sys.account.service.AccountService;
import com.ld.sbjwtjpa.business.sys.admin.model.AdminModel;
import com.ld.sbjwtjpa.business.sys.admin.service.AdminService;
import com.ld.sbjwtjpa.business.sys.jurisdiction.model.JurisdictionModel;
import com.ld.sbjwtjpa.business.sys.jurisdiction.service.JurisdictionService;
import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.model.RolesSubsidiaryModel;
import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.service.RolesSubsidiaryService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class StatelessRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;
    @Resource
    private AdminService adminService;
    @Resource
    private RolesSubsidiaryService rolesSubsidiaryService;
    @Resource
    private JurisdictionService jurisdictionService;

    //    Only StatelessToken type Token is supported.
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    /**
     * @param arg0 当前登陆的实体
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        AccountModel token = (AccountModel) arg0.getPrimaryPrincipal();
        // TODO Auto-generated method stub
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        AccountModel model = new AccountModel();
        model.setUuid(token.getUuid());
        ResponseResult<AccountModel> result = accountService.findOne(model);
        if (result.isSuccess()) {
            info.addRole("admin");
            RolesSubsidiaryModel model1 = new RolesSubsidiaryModel();
            model1.setOrgId(result.getData().getOrgId());
            ResponseResult<List<RolesSubsidiaryModel>> result2 = rolesSubsidiaryService.findAll(model1);
            List<String> list = new ArrayList<>();
            if (result2.isSuccess()) {
                result2.getData().forEach(k -> {
                    list.add(k.getJurId());
                });
                ResponseResult<List<JurisdictionModel>> result3 = jurisdictionService.findByUuidIn(list);
                if (result3.isSuccess()) {
                    result3.getData().forEach(k -> {
                        if (k != null) {
                            info.addRole(k.getJurFlag().split(":")[0]);
                            info.addStringPermission(k.getJurFlag());
                        }
                    });
                }
            }
        } else {
            ResponseResult<AdminModel> result1 = adminService.findById(model.getUuid());
            if (result1.isSuccess())
                info.addRole("admin");
        }
        return info;
    }

    /**
     * 登陆验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        String token = (String) authcToken.getPrincipal();
        if (token == null)
            throw new UnknownAccountException("令牌丢失!");
        Map<String, Claim> map = JWTUtils.verifToken(token);
        if (map == null)
            throw new UnknownAccountException("令牌过期，请从新登陆!");
        String iss = map.get("iss").asString();
        if (!iss.equals("ldtoken"))
            throw new UnknownAccountException("令牌签名不正确!");
        Date nowDate = new Date();
        Date iat = map.get("iat").asDate();
        if (nowDate.before(iat))
            throw new UnknownAccountException("令牌过期，请从新登陆!");
        Date exp = map.get("exp").asDate();
        if (exp.before(nowDate))
            throw new UnknownAccountException("令牌过期，请从新登陆!");

        String account = map.get("sub").asString();
        AccountModel model = new AccountModel();
        model.setAccount(account);
        ResponseResult<AccountModel> result = accountService.findOne(model);
        if (!result.isSuccess())
            throw new UnknownAccountException("当前用户已不存在!");
        return new SimpleAuthenticationInfo(result.getData(), token, getName());
    }

}
