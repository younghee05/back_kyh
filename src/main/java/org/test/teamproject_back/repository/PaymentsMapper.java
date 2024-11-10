package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Payment;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PaymentsMapper {
    List<Payment> findPaymentList(String paymentStatus); // 일 별 매출 목록
    List<Payment> findMonthPaymentList(LocalDate date, String paymentStatus); // 월 별 매출 총합
    int addPayment (Payment payment);
    String findPaymentNumByOrderId (Long orderId);
    int updatePaymentStatus(String paymentStatus, Long orderId);
}
