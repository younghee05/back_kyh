package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.CartItem;

@Builder
@Data
public class ReqModifyCartDto {
    private Long userId;
    private Long cartId;
    private Long cartItemId;
    private int quantity;

    public CartItem toCartItem(Long cartId, Long cartItemId, int quantity) {
        return CartItem.builder()
                .cartId(cartId)
                .cartItemId(cartItemId)
                .quantity(quantity)
                .build();
    }
}
