package org.chason.openapi.controller;

import com.alibaba.fastjson.JSON;
import org.chason.openapi.bean.ResultBean;
import org.chason.openapi.constant.QueryStringConstant;
import org.chason.openapi.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 模拟客户端请求控制器
 *
 * @author chason
 * @date 2018-03-10
 */
@RestController
@RequestMapping("/mock")
public class MockController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAuthService authService;

    private RestTemplate restTemplate = new RestTemplate();
    private String getTonkenUrl = "http://localhost:8081/login/getToken?userId=test&pass=test";

    @RequestMapping(value = "/callHello", method = RequestMethod.GET)
    public ResultBean<String> addBlog() {

        // 获取Token
        StringBuilder url = new StringBuilder("http://localhost:8081/hello");
        StringBuilder queryStr = new StringBuilder();

        ResultBean<String> tokenRes = restTemplate.getForObject(getTonkenUrl, ResultBean.class);

        if (!"200".equals(tokenRes.getCode())) {
            return tokenRes;
        }

        //  添加Token
        queryStr.append(QueryStringConstant.TOKEN).append("=").append(tokenRes.getData()).append("&")
                // 添加时间戳
                .append(QueryStringConstant.TIMESTAMP).append("=").append(System.currentTimeMillis());

        String sign = authService.getSign(queryStr.toString());
        url.append("?").append(queryStr).append("&").append(QueryStringConstant.APPKEY).append("=").append(sign);

        logger.info("Call remote url :{}", url);

        ResultBean<String> remoteCallRes = restTemplate.getForObject(url.toString(), ResultBean.class);

        logger.info("Call remote result :{}", JSON.toJSONString(remoteCallRes));

        return remoteCallRes;
    }

}
