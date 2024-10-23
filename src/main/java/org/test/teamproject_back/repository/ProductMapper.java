package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.entity.ProductCategory;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAllProductsList();
    List<Product> findProductByTitle(String title);
    int findProductCountByTitle(String title);
    int findAllProductCount();
    int findProductCountByCategory(int categoryId);
    int findProductCountBySemiCategory(int categoryId, int semiCategoryId);
    List<Product> findProductByCategory(int categoryId);
    List<Product> findProductBySemiCategory(@Param("main") int categoryId, @Param("sub") int semiCategoryId);
    Product findProductById(Long productId);
    List<Product> findNewProduct();
    List<Product> findPopularityProduct();
}
