package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.entity.OrderItem;
import org.test.teamproject_back.entity.Payment;
import org.test.teamproject_back.repository.PaymentsMapper;
import org.test.teamproject_back.repository.ProductMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentsMapper paymentsMapper;
    @Autowired
    private ProductMapper productMapper;

    public Payment getPaymentNum (Long orderId) {
        return paymentsMapper.findPaymentNumByOrderId(orderId);
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyStatus (String paymentId) {
        String paymentStatus = "failed";
        paymentsMapper.updatePaymentStatus(paymentStatus, paymentId.trim());
        List<OrderItem> orderItems = paymentsMapper.findOrderItems(paymentId.trim());
        productMapper.updateReturnSalesCountAndStock(orderItems);
    }
}
