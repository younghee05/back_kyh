package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long orderId;
    private Long userId;
    private String orderStatus;
    private int totalAmount;
    private String createdAt;

    private List<OrderItem> orderItems;
    private User user;
    private Payment payment;
    private Address address;
}
