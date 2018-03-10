package org.chason.openapi.controller;

import org.chason.openapi.bean.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/hello")
@RestController
public class HelloController {

    @RequestMapping(value = "/sayHello")
    public ResultBean<String> sayHello(@RequestParam(name = "name", required = true) String name) {
        return new ResultBean("hello:" + name);
    }

}
