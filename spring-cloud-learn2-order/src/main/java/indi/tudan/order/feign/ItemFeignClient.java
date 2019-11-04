package indi.tudan.order.feign;

import indi.tudan.order.entity.Item;
import indi.tudan.order.fallback.ItemServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 申明这是一个 Feign 客户端，并且指明服务 id
 */
@FeignClient(value = "app-commodity", fallback = ItemServiceFallback.class)
public interface ItemFeignClient {

    /**
     * 这里定义了类似于 SpringMVC 用法的方法，就可以进行 RESTful 方式的调用了
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    Item queryItemById(@PathVariable("id") Long id);
}