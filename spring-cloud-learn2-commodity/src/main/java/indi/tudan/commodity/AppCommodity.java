package indi.tudan.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"indi.tudan.commodity.controller",
        "indi.tudan.commodity.service", "indi.tudan.commodity.config"})
public class AppCommodity {
    public static void main(String[] args) {
        SpringApplication.run(AppCommodity.class, args);
    }

    /*@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }*/
}
