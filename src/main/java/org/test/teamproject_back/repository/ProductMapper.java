package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.entity.ProductCategory;

import java.util.List;

@Mapper
public interface ProductMapper {
    int addProduct(Product product);
    int addCategory(Category category);
    int addProductCategory(ProductCategory productCategory);
    List<Product> getAllProductsList();
    List<Product> findProductByTitle(String title);
    Product findProductById(Long id);
    int deleteProductById(Long productId);
    int deleteProductCategoryById(Long productId);
    int updateProduct(Product product);
    int updateProductCategory(int categoryId);
}
