package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqCartListDto;
import org.test.teamproject_back.dto.request.ReqOrderDto;
import org.test.teamproject_back.dto.response.RespCartOrderDto;
import org.test.teamproject_back.dto.response.RespOrderDto;
import org.test.teamproject_back.entity.*;
import org.test.teamproject_back.repository.*;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;
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
    private OrderMapper orderMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private PaymentsMapper paymentsMapper;

    @Transactional(rollbackFor = SQLException.class)
    public void addOrder(ReqOrderDto dto) {
        Order order = dto.toOrder();
        orderMapper.addOrder(order);
        orderItemMapper.addOrderItem(order.getOrderId(), dto.getProducts());
        paymentsMapper.addPayment(dto.toPayment(order.getOrderId()));
        productMapper.updateSalesCountAndStock(dto.getProducts());
    }

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
                .addressId(address.getAddressId())
                .address(address.getAddress())
                .detailAddress(address.getDetailAddress())
                .zipCode(address.getZipCode())
                .title(product.getTitle())
                .price(product.getPrice())
                .thumbnailImg(product.getThumbnailImg())
                .build();
    }

    public RespCartOrderDto getCartOrderList(ReqCartListDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Address address = null;
        User user = userMapper.findUserByUserId(principalUser.getId());
        List<CartItem> cartItemList = cartItemMapper.findCartItemList(principalUser.getId(), dto.getId());

        if (dto.getAddress() == null && dto.getDetailAddress() == null && dto.getZipCode() == 0) {
            addressMapper.addAddress(dto.toAddress(principalUser.getId()));
        }

        address = addressMapper.findAddressByUserId(principalUser.getId());

        return RespCartOrderDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .addressId(address.getAddressId())
                .address(address.getAddress())
                .detailAddress(address.getDetailAddress())
                .zipCode(address.getZipCode())
                .cartItemList(cartItemList)
                .build();
    }

    public List<Order> getOrderRecord() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return orderMapper.findUserOrder(principalUser.getId());
    }
}
