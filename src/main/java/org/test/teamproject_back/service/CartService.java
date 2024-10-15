package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqAddCartDto;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;
import org.test.teamproject_back.repository.CartItemMapper;
import org.test.teamproject_back.repository.CartMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Transactional(rollbackFor = SQLException.class)
    public void addCart(ReqAddCartDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = null;

        if (!authentication.getName().equals("anonymousUser")) {
            PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
            if (principalUser.getId() == dto.getUserId()) {
                cart = cartItemMapper.findCartByUserId(dto.getUserId());

                if (cart == null) {
                    cart = dto.toCart();
                    cartMapper.addCart(cart);
                }
                cartItemMapper.addCartItems(dto.toCartItem(cart.getCartId()));
            }
        }
    }
}
