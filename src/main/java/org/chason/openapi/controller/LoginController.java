package org.chason.openapi.controller;

import org.chason.openapi.bean.ResultBean;
import org.chason.openapi.util.TokenUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录控制器
 *
 * @author chason
 * @date 2018-03-10
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/getToken")
    public ResultBean<String> getToken(@RequestParam(value = "userId", required = true) String userId, @RequestParam(value = "pass", required = true) String pass) {

        ResultBean<String> result = new ResultBean<>();
        String token = null;

        // 暂时写死
        if ("test".equals(userId) && ("test").equals(pass)) {
            token = TokenUtil.getTokenStr(userId);
            TokenUtil.put(token, userId);
        }
        result.setData(token);
        return result;
    }

}
