package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;

@Mapper
public interface ProductMapper {
    int addProduct(Product product);
    Product searchProductByTitle(String title);
    int deleteProductById(Long productId);
    int updateProduct(Product product);
    Category findCategoryById(int categoryId);
}
