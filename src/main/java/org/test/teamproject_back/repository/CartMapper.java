package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Cart;

@Mapper
public interface CartMapper {
    int addCart(Cart cart);
}
