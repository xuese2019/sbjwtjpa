package com.ld.sbjwtjpa.business.sys.login.controller;

import com.ld.sbjwtjpa.business.sys.account.model.AccountModel;
import com.ld.sbjwtjpa.business.sys.account.service.AccountService;
import com.ld.sbjwtjpa.sys.shiro.JWTUtils;
import com.ld.sbjwtjpa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Api(value = "登陆接口", description = "登陆接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountService service;
//    @Autowired
//    DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "登陆", notes = "后台带有数据验证")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult<String> login(@Valid @RequestBody AccountModel model,
                                        BindingResult result) {
        model.setOrgId("0");
//        验证输入的信息
        if (result.hasErrors())
            return new ResponseResult<>(false,
                    result.getAllErrors().get(0).getDefaultMessage());
        ResponseResult<AccountModel> result1 = service.findOne(model);
        if (result1.isSuccess()) {
            String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
            if (md5Password.equals(result1.getData().getPassword())) {
                try {
                    String s = JWTUtils.creaToken(result1.getData().getAccount(), result1.getData().getUuid());
                    return new ResponseResult<>(true, result1.getData().getUuid() + "," + result1.getData().getAccount(), s);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseResult<>(false, "令牌生成错误");
                }
            } else
                return new ResponseResult<>(false, "账号密码错误");
        } else
            return new ResponseResult<>(false, "账号密码错误");
    }

//    @RequestMapping(value = "/defaultKaptcha", method = RequestMethod.GET)
//    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            //生产验证码字符串并保存到session中
//            String createText = defaultKaptcha.createText();
//            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
//            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = defaultKaptcha.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Cache-Control", "no-store");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setDateHeader("Expires", 0);
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream =
//                httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }
//
//    //    验证码验证
//    public boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
//        if (captchaId == null)
//            return false;
//        String parameter = httpServletRequest.getParameter("vrifyCode");
//        if (!captchaId.equals(parameter))
//            return false;
//        else
//            return true;
//    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ModelAndView logout(HttpServletResponse response) {
//        SecurityUtils.getSubject().logout();
//        return new ModelAndView("/index");
//    }

}
