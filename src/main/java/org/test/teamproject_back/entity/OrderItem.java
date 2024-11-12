package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private int quantity;
    private int price;

    private Product product;
    private List<Product> products;
}
