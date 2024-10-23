package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;
import org.test.teamproject_back.entity.Product;

import java.util.List;

@Mapper
public interface CartItemMapper {
    int addCartItems(CartItem cartItem);
    int updateCartItems(CartItem cartItem);
    int deleteCartItemByCartItemId(Long cartItemId);
}
