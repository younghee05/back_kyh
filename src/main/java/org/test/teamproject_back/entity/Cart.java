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
public class Cart { // shopping cart tb
    private Long cartId; // id
    private Long userId;
    private List<Product> product; // 타입 고민
    private CartItem cartItem;
    private String createdDate;
}
