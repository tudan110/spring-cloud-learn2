package indi.tudan.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 以 myspcloud 开头的配置被匹配到
 */
@Component
@ConfigurationProperties(prefix = "myspcloud")
public class OrderProperties {

    private ItemProperties item = new ItemProperties();

    public ItemProperties getItem() {
        return item;
    }

    public void setItem(ItemProperties item) {
        this.item = item;
    }
}
