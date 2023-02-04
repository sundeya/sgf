package com.guigu.controller;

import com.atguigu.springcloud.entitis.CommonResult;
import com.atguigu.springcloud.entitis.Payment;
import com.guigu.servoce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping(value="/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        return new CommonResult(200,"插入成功"+serverPort,result);
    }
    @GetMapping(value="/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        return new CommonResult(200,"查询成功"+serverPort,result);
    }
}
