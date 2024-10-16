package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqAddCartDto;
import org.test.teamproject_back.dto.response.RespSearchCartDto;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.repository.CartItemMapper;
import org.test.teamproject_back.repository.CartMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Transactional(rollbackFor = SQLException.class)
    public void addCart(ReqAddCartDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals("anonymousUser")) {
            PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();

            if (principalUser.getId() == dto.getUserId()) { // id는 확인했음
                Long cartId = cartMapper.findCartIdByUserId(dto.getUserId()); // cart 있는지 확인하기
                System.out.println(cartId);
                if (cartId == null) {
                    Cart cart = dto.toCart();
                    System.out.println("cart: " + cart);
                    cartMapper.addCart(cart);
                    cartItemMapper.addCartItems(dto.toCartItem(cart.getCartId()));
                }
                cartItemMapper.addCartItems(dto.toCartItem(cartId));
            }
        }
    }

    public void searchCart(Long userId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principalUser.getId() != userId) {
            throw new RuntimeException("잘못 된 접근 입니다.");
        }

        List<Cart> cart = cartItemMapper.findCartListByUserId(userId);
        System.out.println(cart);

    }
}
