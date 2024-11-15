package com.paul.project.blackfilter;


import com.paul.project.utils.NetUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 全局IP黑名单过滤
 */
//这个注解就会自动生效判断IP
@WebFilter(urlPatterns = "/*",filterName = "blackFilter")
public class BlackIpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ipAddress = NetUtils.getIpAddress(HttpServletRequest.class.cast(request));
        if(BlackIpUtils.isBlackIp(ipAddress)){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("{\"errorCode\":\"-1\",\"errorMessage\":\"黑名单IP，禁止访问\"}");
            return;
        }
        chain.doFilter(request, response);
    }
}
