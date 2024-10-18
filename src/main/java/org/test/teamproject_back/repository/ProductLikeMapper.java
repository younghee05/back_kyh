package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.ProductLike;

@Mapper
public interface ProductLikeMapper {
    Long addProductLike(ProductLike like);
    Long deleteProductLike(ProductLike dislike);
    int getLikeCountByProductId(int productId);
    ProductLike SearchProductLikeByUserId(Long userId);
}
