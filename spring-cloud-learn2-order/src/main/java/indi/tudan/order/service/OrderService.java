package indi.tudan.order.service;

import indi.tudan.order.entity.Item;
import indi.tudan.order.entity.Order;
import indi.tudan.order.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private static final Map<String, Order> ORDER_DATA = new HashMap<>();

    static {
        // 模拟数据库，构造测试数据
        Order order1 = new Order();
        order1.setOrderId("1");
        order1.setCreateDate(new Date());
        order1.setUpdateDate(order1.getCreateDate());
        order1.setUserId(1L);
        List<OrderDetail> orderDetails1 = new ArrayList<>();

        Item item = new Item();// 此处并没有商品的数据，只是保存了商品ID，需要调用商品微服务获取
        item.setId(1L);
        orderDetails1.add(new OrderDetail(order1.getOrderId(), item));

        item = new Item(); // 构造第二个商品数据
        item.setId(6L);
        orderDetails1.add(new OrderDetail(order1.getOrderId(), item));
        order1.setOrderDetails(orderDetails1);
        ORDER_DATA.put(order1.getOrderId(), order1);

        Order order2 = new Order();
        order2.setOrderId("2");
        order2.setCreateDate(new Date());
        order2.setUpdateDate(order2.getCreateDate());
        order2.setUserId(3L);
        List<OrderDetail> orderDetails2 = new ArrayList<OrderDetail>();

        Item item2 = new Item();// 此处并没有商品的数据，只是保存了商品ID，需要调用商品微服务获取
        item2.setId(3L);
        orderDetails2.add(new OrderDetail(order2.getOrderId(), item2));

        item2 = new Item(); // 构造第二个商品数据
        item2.setId(5L);
        orderDetails2.add(new OrderDetail(order2.getOrderId(), item2));
        order2.setOrderDetails(orderDetails2);
        ORDER_DATA.put(order2.getOrderId(), order2);
    }

    @Autowired
    private ItemService itemService;

    /**
     * 根据订单id查询订单数据
     *
     * @param orderId
     * @return
     */
    public Order queryOrderById(String orderId) {
        Order order = ORDER_DATA.get(orderId);
        if (null == order) {
            return null;
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            // 通过商品微服务查询商品详细数据
            Item item = this.itemService.queryItemById2(orderDetail.getItem()
                    .getId());
            if (null == item) {
                continue;
            }
            orderDetail.setItem(item);
        }

        return order;
    }

    public Order queryOrderByIdx(String orderId) {
        Order order = ORDER_DATA.get(orderId);
        if (null == order) {
            return null;
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            // 通过商品微服务查询商品详细数据
            Item item = this.itemService.queryItemById3(orderDetail.getItem()
                    .getId());
            if (null == item) {
                continue;
            }
            orderDetail.setItem(item);
        }

        return order;
    }
}
