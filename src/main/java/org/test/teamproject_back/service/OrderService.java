package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqCartListDto;
import org.test.teamproject_back.dto.response.RespCartOrderDto;
import org.test.teamproject_back.dto.response.RespOrderDto;
import org.test.teamproject_back.entity.*;
import org.test.teamproject_back.repository.*;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    public RespOrderDto getOrderList(Long productId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userMapper.findUserByUserId(principalUser.getId());
        Product product = productMapper.findProductById(productId);
        Address address = addressMapper.findAddressByUserId(principalUser.getId());

        return RespOrderDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .point(user.getPoint())
                .addressId(address.getAddressId())
                .address(address.getAddress())
                .detailAddress(address.getDetailAddress())
                .title(product.getTitle())
                .thumbnailImg(product.getThumbnailImg())
                .build();
    }

    public RespCartOrderDto getCartOrderList(ReqCartListDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userMapper.findUserByUserId(principalUser.getId());

        List<Long> cartItemsIdList = cartMapper.findCartItemIdByCartId(dto.getCartId()); // 카트에 해당하는 아이템
        System.out.println(cartItemsIdList); // 나옴
        List<Long> matchingCartItemIdList = cartItemsIdList.stream() // 해당 아이템 찾음
                .filter(cartItemId -> cartItemId.equals(dto.getCartItemId()))
                .collect(Collectors.toList());
        List<Cart> cartList = null;
        System.out.println(matchingCartItemIdList); // 나옴

        if (!matchingCartItemIdList.isEmpty()) {
            for (Long cartItemId : matchingCartItemIdList) {
                cartList = cartMapper.findCartListByCartItemId(cartItemId);
            }
        }
        System.out.println(cartList);
        Address address = addressMapper.findAddressByUserId(principalUser.getId());

        return RespCartOrderDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .point(user.getPoint())
                .addressId(address.getAddressId())
                .address(address.getAddress())
                .detailAddress(address.getDetailAddress())
                .cartList(cartList)
                .build();
    }
}
