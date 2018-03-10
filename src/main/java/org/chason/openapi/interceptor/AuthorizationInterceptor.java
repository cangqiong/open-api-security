package org.chason.openapi.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.chason.openapi.exception.BuinessException;
import org.chason.openapi.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * 自定义拦截器，验证第三方是否有权限访问接口
 *
 * @author chason
 * @date 2018-03-10
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = false;
        // 将请求转换成utf-8编码
        request.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL() + "?" + request.getQueryString();
        logger.info("访问URL地址为：{}", url);

        String requestMethod = request.getMethod();
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString();
        queryString = URLDecoder.decode(queryString, "utf-8");
        logger.info("请求方法为：{}，请求路径为：{}，请求参数为：{}", requestMethod, requestUri, queryString);

        try {
            result = authService.validate(requestMethod, requestUri, queryString);
        } catch (BuinessException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject res = new JSONObject();
            res.put("code", e.getCode());
            res.put("msg", e.getMsg());
            PrintWriter out = response.getWriter();
            out.append(res.toJSONString());
            return false;
        }

        return result;
    }

}
