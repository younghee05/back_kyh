package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.OrderItem;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    int addOrderItem (Long orderId, List<OrderItem> orderItemList);
}
