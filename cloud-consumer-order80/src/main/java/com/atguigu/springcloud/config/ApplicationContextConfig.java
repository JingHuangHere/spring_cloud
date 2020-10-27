package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
/*    用了ribbon以后就不需要resttemplate*/
 /* @LoadBalanced*/
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
