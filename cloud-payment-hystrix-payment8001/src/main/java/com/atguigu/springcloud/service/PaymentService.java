package com.atguigu.springcloud.service;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "总线程： " + Thread.currentThread().getName() + " paymentInfo_OK, id: " +
                id + "\t" + "haha";
    }

    //
    // 3s 以内是正常的业务逻辑，超过3s
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
       // int age = 10/0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch ( InterruptedException e) {
            e.printStackTrace();
        }
        return "总线程： " + Thread.currentThread().getName() + " paymentInfo_TimeOut, id: " +
                id + "\t" + "耗时(s)" + timeNumber;
    }


    public String paymentInfo_TimeOutHandler(Integer id) {
         return "总线程： " + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler, id: " +
                id + "\t" + "， 系统繁忙，请稍后再实" ;
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
             @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  // 时候开启熔断器
             @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
             @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
             @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号： " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试，ID： " + id;
    }
}
