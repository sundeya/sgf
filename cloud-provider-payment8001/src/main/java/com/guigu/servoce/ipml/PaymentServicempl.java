package com.guigu.servoce.ipml;


import com.atguigu.springcloud.entitis.Payment;
import com.guigu.dao.PaymentDao;
import com.guigu.servoce.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class PaymentServicempl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
