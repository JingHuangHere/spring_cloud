package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;

@Configuration
public class GateWayConfig {

    @Bean
    // 对比yml文件配置方法
    public RouteLocator customRouteLoader(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // http://news.baidu.com/guonei
        routes.route("path_route_atguigu",
                      r -> r.path("/guonei")
                      .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
