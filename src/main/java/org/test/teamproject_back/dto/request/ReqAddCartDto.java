package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;

@Builder
@Data
public class ReqAddCartDto {
    private Long userId;
    private Long productId;
    private int quantity;
    private int price;

    public Cart toCart() {
        return Cart.builder()
                .userId(userId)
                .build();
    }

    public CartItem toCartItem(Long cartId) {
        return CartItem.builder()
                .cartId(cartId)
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
