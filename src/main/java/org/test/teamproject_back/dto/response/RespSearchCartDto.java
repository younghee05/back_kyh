package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Cart;

import java.util.List;

@Builder
@Data
public class RespSearchCartDto {
    private List<Cart> cartList;
    private Long totalAmount;
}
