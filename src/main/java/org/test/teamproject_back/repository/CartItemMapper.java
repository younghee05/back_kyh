package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;

@Mapper
public interface CartItemMapper {
    int addCartItems(CartItem cartItem);
    Cart findCartByUserId(Long userId);
}
