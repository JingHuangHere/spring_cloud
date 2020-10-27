package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

// 这个实际上就是Repository
@Mapper
public interface PaymentDao
{
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
