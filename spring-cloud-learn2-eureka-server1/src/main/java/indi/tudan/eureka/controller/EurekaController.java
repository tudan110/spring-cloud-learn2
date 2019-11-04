package indi.tudan.eureka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaController {

    @RequestMapping("test/eureka")
    public String fun1() {
        return "这是Eureka注册中心！！";
    }
}
