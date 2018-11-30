package com.ld.sbjwtjpa.sys.shiro;

import com.ld.sbjwtjpa.utils.JackJson;
import com.ld.sbjwtjpa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * filter all requests
 */
@Slf4j
public class StatelessAuthcFilter extends AccessControlFilter {

    /**
     * Only the current method returns to false,
     * and the following method will execute.
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**
     * the current metod returns to false to end the current request.
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        To get token,you can use head/Cokie or others.
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String lTokenD = request.getHeader("LTokenD");
        log.info("全局拦截器url:" + request.getRequestURI());
        log.info("全局拦截器token:" + lTokenD);
        if (lTokenD == null || lTokenD.isEmpty())
            tokenError(servletRequest, servletResponse);
        else {
//            Generate stateless token
            StatelessToken token = new StatelessToken();
            token.setToken(lTokenD);
//                Entrust realm to login
            try {
                getSubject(servletRequest, servletResponse).login(token);
                return true;
            } catch (Exception e) {
                tokenError(servletRequest, servletResponse);
            }
        }
        return false;
    }

    //    Is no Token
    private void tokenError(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        ResponseResult<String> result = new ResponseResult<>(false, "logout");
        httpServletResponse.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
    }

    //  Is it Ajax
    private boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
