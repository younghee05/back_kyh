package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Order;

import java.util.List;

@Mapper
public interface AdminOrderMapper {
    List<Order> findAllOrderList();
}
