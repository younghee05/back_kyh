package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.entity.Order;
import org.test.teamproject_back.repository.AdminOrderMapper;

import java.util.List;

@Service
public class AdminOrderService {

    @Autowired
    private AdminOrderMapper adminOrderMapper;

    public List<Order> getOrderList() {
        return adminOrderMapper.findAllOrderList();
    }
}
