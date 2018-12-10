package com.ld.sbjwtjpa;

import com.ld.sbjwtjpa.utils.MyExceptions;
import com.ld.sbjwtjpa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.AuthenticationFailedException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

//import org.hibernate.JDBCException;

/**
 * 全局错误处理
 * LD
 */
@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    /**
     * 自定义未找到数据
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MyExceptions.class)
    public ResponseResult<String> myExceptions(HttpServletRequest request,
                                               Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        String errorStr = "";
        switch (exception.getMessage()) {
            case "1":
                errorStr = "未查询到记录";
                break;
            case "2":
                errorStr = "数据过于陈旧，请刷新后在尝试";
                break;
            case "3":
                errorStr = "需要操作的对象的主键不能为空";
                break;
            case "4":
                errorStr = "数据已存在";
                break;
            case "5":
                errorStr = "未找到需要操作的数据";
                break;
            default:
                errorStr = exception.getMessage();
        }
        result.setMessage(errorStr);
        return result;
    }

    /**
     * 非法参数
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseResult<String> constraintViolationException(HttpServletRequest request,
                                                               Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage(exception.getMessage());
        return result;
    }

    /**
     * 数据类型转换错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = UnexpectedTypeException.class)
    public ResponseResult<String> unexpectedTypeException(HttpServletRequest request,
                                                          Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据类型转换错误");
        return result;
    }

    /**
     * 实体不存在
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseResult<String> entityNotFoundException(HttpServletRequest request,
                                                          Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据未找到，请确认传入数据的准确性");
        return result;
    }

    /**
     * 邮箱错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = AuthenticationFailedException.class)
    public ResponseResult<String> authenticationFailedException(HttpServletRequest request,
                                                                Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("邮箱无法登陆");
        return result;
    }

    @ExceptionHandler(value = ConversionFailedException.class)
    public ResponseResult<String> conversionFailedException(HttpServletRequest request,
                                                            Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("表单类型转换错误");
        return result;
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseResult<String> mySQLIntegrityConstraintViolationException(HttpServletRequest request,
                                                                             Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据异常，请确认是否违反唯一值、数据类型错误、必填项等错误");
        return result;
    }

    /**
     * 非法参数
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ParseException.class)
    public ResponseResult<String> parseException(HttpServletRequest request,
                                                 Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据转换错误，请确认日期，数字等格式是否正确");
        return result;
    }

    /**
     * 非法参数
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseResult<String> illegalArgumentException(HttpServletRequest request,
                                                           Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("非法参数错误，请确认数据准确性");
        return result;
    }

    /**
     * office 文件操作错误
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
//    @ExceptionHandler(value = POIXMLException.class)
//    public ResponseResult<String> pOIXMLException(HttpServletRequest request,
//                                                  Exception exception) throws Exception {
//        exception.printStackTrace();
//        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
//        ResponseResult<String> result = new ResponseResult<>();
//        result.setSuccess(false);
//        result.setMessage("office 文件操作错误");
//        return result;
//    }

    /**
     * 不支持的字符集
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = UnsupportedEncodingException.class)
    public ResponseResult<String> unsupportedEncodingException(HttpServletRequest request,
                                                               Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("不支持的字符集，当前方法所采用的字符集为UTF-8");
        return result;
    }

    /**
     * 权限不足
     *
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseResult<String> authorizationException(Exception exception) throws Exception {
        //        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        return new ResponseResult<>(false, new String("权限不足".getBytes(), StandardCharsets.UTF_8));
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseResult<String> unauthorizedException(Exception exception) throws Exception {
        //        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        return new ResponseResult<>(false, new String("权限不足".getBytes(), StandardCharsets.UTF_8));
    }

    /**
     * 类型强制转换错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ClassCastException.class)
    public ResponseResult<String> ClassCastException(HttpServletRequest request,
                                                     Exception exception) {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("类型强制转换错误，请确认数据准确性");
        return result;
    }


    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public ResponseResult<String> ArrayIndexOutOfBoundsException(HttpServletRequest request,
                                                                 Exception exception) {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数组下标越界错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseResult<String> FileNotFoundException(HttpServletRequest request,
                                                        Exception exception) {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("文件未找到错误");
        return result;
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseResult<String> SQLException(HttpServletRequest request,
                                               Exception exception) {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("操作数据库错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseResult<String> IOException(HttpServletRequest request,
                                              Exception exception) {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("输入输出错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = NoSuchMethodException.class)
    public ResponseResult<String> NoSuchMethodException(HttpServletRequest request,
                                                        Exception exception) {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("方法未找到错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = OutOfMemoryError.class)
    public ResponseResult<String> OutOfMemoryError(HttpServletRequest request,
                                                   Exception exception) {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("内存不足错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = StackOverflowError.class)
    public ResponseResult<String> StackOverflowError(HttpServletRequest request,
                                                     Exception exception) {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("堆栈溢出错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = VirtualMachineError.class)
    public ResponseResult<String> VirtualMachineError(HttpServletRequest request,
                                                      Exception exception) {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("虚拟机错误，请确认数据准确性");
        return result;
    }

//    @ExceptionHandler(value = JDBCException.class)
//    @ResponseBody
//    public String JDBCException(HttpServletRequest request,
//                                Exception exception) throws Exception {
//        //exception.printStackTrace();
//        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getSuppressed() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getStackTrace() + "::::::" + new Date());
//        return "JDBCException，请确认数据准确性";
//    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseResult<String> nullPointerException(HttpServletRequest request,
                                                       Exception exception) {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据未找到");
        return result;
    }

    @ExceptionHandler(value = org.xml.sax.SAXException.class)
    public ResponseResult<String> SAXException(HttpServletRequest request,
                                               Exception exception) {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("页面存在多个<!DOCTYPE html>");
        return result;
    }

    @ExceptionHandler(value = org.xml.sax.SAXParseException.class)
    public ResponseResult<String> SAXParseException(HttpServletRequest request,
                                                    Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("class 重复");
        return result;
    }

    @ExceptionHandler(value = org.springframework.beans.factory.BeanCreationException.class)
    public void BeanCreationException(HttpServletRequest request,
                                      Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        System.out.println("请购买此产品");
    }

    @ExceptionHandler(value = CannotGetJdbcConnectionException.class)

    public ResponseResult<String> CannotGetJdbcConnectionException(HttpServletRequest request,
                                                                   Exception exception) {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据库链接错误");
        return result;
    }

//    @ExceptionHandler(value = TemplateInputException.class)
//    public ResponseResult<String> TemplateInputException(HttpServletRequest request,
//                                                         Exception exception) {
//        exception.printStackTrace();
//        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
//        ResponseResult<String> result = new ResponseResult<>();
//        result.setSuccess(false);
//        result.setMessage("页面错误");
//        return result;
//    }
}
