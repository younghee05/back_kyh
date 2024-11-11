package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.teamproject_back.dto.request.ReqSearchSalesDto;
import org.test.teamproject_back.entity.Payment;
import org.test.teamproject_back.entity.Product;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PaymentsMapper {
    List<Payment> findPaymentList(String paymentStatus); // 일 별 매출 목록
    List<Payment> findMonthPaymentList(LocalDate date, String paymentStatus); // 월 별 매출 총합
    int addPayment (Payment payment);
    Payment findPaymentNumByOrderId (Long orderId);
    int updatePaymentStatus(String paymentStatus, String paymentId);
    List<Product> getGraphData();
    int findPaymentCount(@Param("limit") int limit, @Param("paymentStatus") String paymentStatus, @Param("startIndex") int startIndex);
}
