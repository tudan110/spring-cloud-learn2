package indi.tudan.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"indi.tudan.commodity.controller", "indi.tudan.commodity.service"})
public class AppCommodity {
    public static void main(String[] args) {
        SpringApplication.run(AppCommodity.class, args);
    }
}
