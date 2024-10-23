package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqAddCartDto;
import org.test.teamproject_back.dto.request.ReqDeleteCartDto;
import org.test.teamproject_back.dto.request.ReqModifyCartDto;
import org.test.teamproject_back.dto.response.RespSearchCartDto;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;
import org.test.teamproject_back.repository.CartItemMapper;
import org.test.teamproject_back.repository.CartMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Transactional(rollbackFor = SQLException.class)
    public void addCart(ReqAddCartDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long cartId = cartMapper.findCartIdByUserId(principalUser.getId());

        if (cartId == null) {
            Cart cart = dto.toCart(principalUser.getId());
            cartMapper.addCart(cart);
            cartItemMapper.addCartItems(dto.toCartItem(cart.getCartId()));
            return;
        }
        cartItemMapper.addCartItems(dto.toCartItem(cartId));
    }

    public RespSearchCartDto getCart() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<Cart> cartList = cartMapper.findCartListByUserId(principalUser.getId());

        return RespSearchCartDto.builder()
                .cartList(cartList)
                .build();
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyCart(ReqModifyCartDto dto) {
        List<Long> cartItemsIdList = cartMapper.findCartItemIdByCartId(dto.getCartId()); // 카트에 해당하는 아이템
        List<Long> matchingCartItemIdList = cartItemsIdList.stream() // 해당 아이템 찾음
                .filter(cartItemId -> cartItemId.equals(dto.getCartItemId()))
                .collect(Collectors.toList());

        if (!matchingCartItemIdList.isEmpty()) {
            for (Long cartItemId : matchingCartItemIdList) {
                cartItemMapper.updateCartItems(dto.toCartItem(dto.getCartId(), cartItemId, dto.getQuantity()));
            }
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deleteCart(ReqDeleteCartDto dto) {
        cartItemMapper.deleteCartItemByCartItemId(dto.getCartItemId());
    }
}