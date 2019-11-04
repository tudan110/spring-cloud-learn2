package indi.tudan.order.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import indi.tudan.order.entity.Item;
import indi.tudan.order.feign.ItemFeignClient;
import indi.tudan.order.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    OrderProperties orderProperties;

    @Autowired
    private ItemFeignClient itemFeignClient;

    /**
     * Spring框架对 RESTful 方式的 http 请求做了封装，来简化操作
     */
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    //@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById3(Long id) {
        Item result = itemFeignClient.queryItemById(id);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }

    /**
     * 请求失败执行的方法
     * fallbackMethod的方法参数个数类型要和原方法一致
     *
     * @param id
     * @return
     */
    /*public Item queryItemByIdFallbackMethod(Long id) {
        return new Item(id, "查询商品信息出错!", null, null, null);
    }*/

    public Item queryItemById2(Long id) {
        // 该方法走eureka注册中心调用(这种方式必须先开启负载均衡@LoadBalanced)
        String itemUrl = "http://app-commodity/item/{id}";
        Item result = restTemplate.getForObject(itemUrl, Item.class, id);
        System.out.println("===========普通queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }

    public Item queryItemById1(Long id) {
        System.out.println("queryItemById1");
        String serviceId = "app-commodity";
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if (instances.isEmpty()) {
            return null;
        }
        // 为了演示，在这里只获取一个实例
        ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
    }
}