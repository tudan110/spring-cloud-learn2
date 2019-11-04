package indi.tudan.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关过滤器
 * 加入到 Spring 容器
 */
@Component
public class UserLoginZuulFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {

        // 该过滤器需要执行
        return true;
    }

    @Override
    public Object run() {

        // 编写业务逻辑
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {

            // 过滤该请求，不对其进行路由
            requestContext.setSendZuulResponse(false);

            // 设置响应状态码
            requestContext.setResponseStatusCode(401);

            // 设置响应状态码
            requestContext.setResponseBody("token is empty!!");

            return null;
        }
        return null;
    }

    @Override
    public String filterType() {

        // 设置过滤器类型为：pre
        return "pre";
    }

    @Override
    public int filterOrder() {

        // 设置执行顺序为 0
        return 0;
    }

}