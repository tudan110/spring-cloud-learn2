package indi.tudan.gateway.runner;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@EnableZuulProxy
@EnableSwagger2Doc
@SpringBootApplication
@ComponentScan(basePackages = "indi.tudan.gateway.filter")
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        ZuulProperties properties = new ZuulProperties();
        System.out.println("properties:" + properties);
        return properties;
    }

    /**
     * 配置Swagger
     */
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {

        @Override
        public List<SwaggerResource> get() {
            List resource = new ArrayList<>();
            //name可以随便写，location前缀要与zuul配置的path一致
            resource.add(swaggerResource("myapp-commodity", "/item-service/v2/api-docs?token=1", "2.0"));
            resource.add(swaggerResource("myapp-order", "/order-service/v2/api-docs?token=1", "2.0"));
            return resource;
        }

        /**
         * name可以随便写，location前缀要与zuul配置的path一致
         */
        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }

}