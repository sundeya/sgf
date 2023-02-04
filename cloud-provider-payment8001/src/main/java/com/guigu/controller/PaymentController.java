package com.guigu.controller;

import com.atguigu.springcloud.entitis.CommonResult;
import com.atguigu.springcloud.entitis.Payment;
import com.guigu.servoce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
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
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services=discoveryClient.getServices();
        for(String service:services){
            log.info("*******service"+service);
        }
        List<ServiceInstance> instnces=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instnces){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
