package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.response.RespOrderDto;
import org.test.teamproject_back.entity.Order;
import org.test.teamproject_back.repository.OrderMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public RespOrderDto getOrderList() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Order orderList = orderMapper.getOrderList(principalUser.getId());
        System.out.println(orderList);
        return RespOrderDto.builder()
                .order(orderList)
                .build();
    }
}
