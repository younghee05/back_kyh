package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {
    private Long cartItemId;
    private Long cartId;
    private Long productId;
    private int quantity;
    private int price;
    private Product product;
}
