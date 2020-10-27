package springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements OrderHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "---PaymentFallBackService fall back paymentok";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---PaymentFallBackService fall back paymen timeout";
    }
}

