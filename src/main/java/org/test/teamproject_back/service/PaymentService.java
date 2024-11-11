package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.entity.Payment;
import org.test.teamproject_back.repository.PaymentsMapper;

import java.sql.SQLException;

@Service
public class PaymentService {

    @Autowired
    private PaymentsMapper paymentsMapper;

    public Payment getPaymentNum (Long orderId) {
        return paymentsMapper.findPaymentNumByOrderId(orderId);
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyStatus (String paymentId) {
        String paymentStatus = "failed";
        paymentsMapper.updatePaymentStatus(paymentStatus, paymentId.trim());

    }
}
