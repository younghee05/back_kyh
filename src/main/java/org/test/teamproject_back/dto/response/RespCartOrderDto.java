package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;

import java.util.List;

@Builder
@Data
public class RespCartOrderDto {
    private Long userId;
    private String name;
    private String email;
    private Long addressId;
    private String address;
    private String detailAddress;
    private int zipCode;
    private List<CartItem> cartItemList;
}
