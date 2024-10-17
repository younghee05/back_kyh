package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqAddCartDto;
import org.test.teamproject_back.dto.request.ReqModifyCartDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.dto.response.RespSearchCartDto;
import org.test.teamproject_back.entity.Cart;
import org.test.teamproject_back.entity.CartItem;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.exception.UnauthorizedAccessException;
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
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principalUser.getId() == dto.getUserId()) {
            Long cartId = cartMapper.findCartIdByUserId(dto.getUserId());

            if (cartId == null) {
                Cart cart = dto.toCart();
                cartMapper.addCart(cart);
                cartItemMapper.addCartItems(dto.toCartItem(cart.getCartId()));
                return;
            }
            cartItemMapper.addCartItems(dto.toCartItem(cartId));
        }

    }

    public RespSearchCartDto searchCart(Long userId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principalUser.getId() != userId) {
            throw new UnauthorizedAccessException("본인 인증이 필요합니다.");
        }

        List<Cart> cartList = cartItemMapper.findCartListByUserId(userId);
        List<CartItem> cartItemList = cartItemMapper.findCartItemListByUserId(userId);
        System.out.println(cartItemList);

        Long totalAmount = cartItemList.stream()
                .mapToLong(
                        cartItem -> cartItem.getPrice() * cartItem.getQuantity()
                )
                .sum();

        return RespSearchCartDto.builder()
                .cartList(cartList)
                .totalAmount(totalAmount)
                .build();
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyCart(ReqModifyCartDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principalUser.getId() != dto.getUserId()) {
            throw new UnauthorizedAccessException("본인 인증이 필요합니다.");
        }

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
}