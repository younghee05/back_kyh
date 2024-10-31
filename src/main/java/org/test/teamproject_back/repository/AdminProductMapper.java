package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.entity.ProductCategory;

import java.util.List;

@Mapper
public interface AdminProductMapper {
    int addProduct(Product product);
    int addCategory(Category category);
    int addSemiCategory(Category semiCategory);
    int addProductCategory(ProductCategory productCategory);
    int updateProduct(Product product);
    int updateProductCategory(ProductCategory productCategory);
    int deleteProductById(List<Long> checkedIds);
    int deleteProductCategoryById(List<Long> checkedIds);
}
