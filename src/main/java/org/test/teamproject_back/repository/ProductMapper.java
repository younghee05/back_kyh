package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Product;

@Mapper
public interface ProductMapper {
    int addProduct(Product product);
}
