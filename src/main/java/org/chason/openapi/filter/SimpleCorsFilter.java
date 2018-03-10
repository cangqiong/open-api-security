package org.chason.openapi.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCorsFilter implements Filter {

    @Value( "${Origin}" )
    private String origin;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;

        // 它的值要么是请求时Origin字段的值，要么是一个*，表示接受任意域名的请求,解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", reqs.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Origin", origin);
        // 允许跨域设置cookie,表示是否允许发送Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "Origin, X-Requested-With, Content-Type, Accept,If-Modified-Since");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, Last-Modified, Cache-Control, Expires, Content-Type,Accept");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}