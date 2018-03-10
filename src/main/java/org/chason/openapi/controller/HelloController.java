package org.chason.openapi.controller;

import org.chason.openapi.bean.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public ResultBean<String> sayHello() {
        return new ResultBean("hello");
    }

}
