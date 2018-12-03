package com.ld.sbjwtjpa.business.sys.emails.controller;

import com.ld.sbjwtjpa.business.sys.account.model.AccountModel;
import com.ld.sbjwtjpa.business.sys.account.service.AccountService;
import com.ld.sbjwtjpa.business.sys.emails.model.EmailsModel;
import com.ld.sbjwtjpa.sys.shiro.JWTUtils;
import com.ld.sbjwtjpa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Api(value = "邮件接口", description = "邮件接口")
@RestController
@RequestMapping("/emails")
public class EmailsController {

    @Autowired
    private AccountService accountService;
//    @Autowired
//    private JavaMailSender mailSender;

    @ApiOperation(value = "发送邮件", notes = "后台带有数据验证")
    @RequestMapping(value = "/emails", method = RequestMethod.POST)
    public ResponseResult<String> toPeople(HttpServletRequest request,
                                           @Valid @RequestBody EmailsModel model,
                                           BindingResult result) {
//        验证输入的信息
        if (result.hasErrors())
            return new ResponseResult<>(false,
                    result.getAllErrors().get(0).getDefaultMessage());
        String accId = JWTUtils.getAccId(request);
        if (accId == null)
            return new ResponseResult<>(false, "logout");
        AccountModel model1 = new AccountModel();
        model.setUuid(accId);
        ResponseResult<AccountModel> result1 = accountService.findOne(model1);
        if (!result1.isSuccess())
            return new ResponseResult<>(false, "当前账户已不存在");
        if ((result1.getData().getEmails() == null ||
                result1.getData().getEmailsAuthorizationCode() == null ||
                result1.getData().getEmailsHost() == null) ||
                (result1.getData().getEmails().isEmpty() ||
                        result1.getData().getEmailsAuthorizationCode().isEmpty() ||
                        result1.getData().getEmailsHost().isEmpty()))
            return new ResponseResult<>(false, "请先完善邮箱信息");

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(result1.getData().getEmailsHost());
        sender.setPort(25);
        sender.setUsername(result1.getData().getEmails());
        sender.setPassword(result1.getData().getEmailsAuthorizationCode());
        sender.setDefaultEncoding("utf-8");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(result1.getData().getEmails());
        message.setTo(model.getEmToPeople());
        message.setSubject(model.getEmTitles());
        message.setText(model.getEmBodys());
        sender.send(message);
        return new ResponseResult<>(true, "成功");
    }

    @ApiOperation(value = "获取邮件列表", notes = "分页")
    @RequestMapping(value = "/emails", method = RequestMethod.GET)
    public ResponseResult<List<EmailsModel>> toPeople(HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        if (accId == null)
            return new ResponseResult<>(false, "logout");
        AccountModel model = new AccountModel();
        model.setUuid(accId);
        ResponseResult<AccountModel> result = accountService.findOne(model);
        if (!result.isSuccess())
            return new ResponseResult<>(false, "当前账户已不存在");

        List<EmailsModel> list = new ArrayList<>();
        Message[] emails = findEmails(result.getData().getEmails(), result.getData().getEmailsAuthorizationCode(), result.getData().getEmailsImap());
        if (emails == null)
            return new ResponseResult<>(false, "邮件列表获取失败");
        for (Message message : emails) {
            String subject = message.getSubject();// 获得邮件主题
            Address from = (Address) message.getFrom()[0];// 获得发送者地址
            EmailsModel model1 = new EmailsModel();
            model1.setEmTitles(subject);
            model1.setEmToPeople(from.toString());
            list.add(model1);
//            model1.setEmBodys(message);
        }
        return new ResponseResult<>(true, "成功", list);
    }

    private static Message[] findEmails(String username, String password, String pop) throws Exception {
        // 准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", pop);
        props.setProperty("mail.imap.port", "143");

        // 创建Session实例对象
        Session session = Session.getInstance(props);

        // 创建IMAP协议的Store对象
        Store store = session.getStore("imap");

        // 连接邮件服务器
        store.connect(username, password);

        // 获得收件箱
        Folder folder = store.getFolder("INBOX");
        // 以读写模式打开收件箱
        folder.open(Folder.READ_WRITE);

        // 获得收件箱的邮件列表
        Message[] messages = folder.getMessages();
        store.close();
        return messages;

//        String imapserver = "imap.163.com"; // 邮件服务器
        // 获取默认会话
//        Properties prop = System.getProperties();
//        prop.put("mail.imap.host", pop);
//
////        prop.put("mail.imap.auth.plain.disable", "true");
//        Session mailsession = Session.getInstance(prop, null);
//        mailsession.setDebug(false); //是否启用debug模式
//        IMAPFolder folder = null;
//        IMAPStore store = null;
//        int total = 0;
//        try {
//            store = (IMAPStore) mailsession.getStore("imap");  // 使用imap会话机制，连接服务器
//            store.connect(username, password);
//            folder = (IMAPFolder) store.getFolder("inbox"); //收件箱
//            // 使用只读方式打开收件箱
//            if (!folder.isOpen())
//                return null;
//            folder.open(Folder.READ_WRITE);
//            //获取总邮件数
//            total = folder.getMessageCount();
//            System.out.println("-----------------您的邮箱共有邮件：" + total + " 封--------------");
//            // 得到收件箱文件夹信息，获取邮件列表
//            Message[] msgs = folder.getMessages();
//            System.out.println("\t收件箱的总邮件数：" + msgs.length);
//            System.out.println("\t未读邮件数：" + folder.getUnreadMessageCount());
//            System.out.println("\t新邮件数：" + folder.getNewMessageCount());
//            System.out.println("----------------End------------------");
//        } catch (MessagingException ex) {
//            log.info("不能以读写方式打开邮箱!");
//            return null;
//        } finally {
//            // 释放资源
//            try {
//                if (folder != null)
//                    folder.close(true); //退出收件箱时,删除做了删除标识的邮件
//                if (store != null)
//                    store.close();
//            } catch (Exception bs) {
//                bs.printStackTrace();
//            }
//        }
    }
}
