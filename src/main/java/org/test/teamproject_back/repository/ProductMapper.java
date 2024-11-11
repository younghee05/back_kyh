package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.OrderItem;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.entity.ProductCategory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

@Mapper
public interface ProductMapper {
//    List<Product> findAllProductsList(Map<String, Object> paging);
    List<Product> findAllProductsList(int startIndex, int limit);
    List<Product> findProduct(Map<String, Object> paging);
    int findProductCountByTitle(String title);
    int findAllProductCount();
    int findProductCountBySemiCategory(int mainCategoryId, int semiCategoryId);
    List<Product> findProductByCategory(Map<String, Object> paging);
    Product findProductById(Long productId);
    List<Product> findNewProduct();
    List<Product> findPopularityProduct();
    List<Product> findRecommendedProduct();
    int updateSalesCountAndStock(List<OrderItem> orderItemList);
}
