package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.ProductLike;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Mapper
public interface ProductLikeMapper {
    Long addProductLike(ProductLike like);
    Long deleteProductLike(ProductLike dislike);
    int getLikeCountByProductId(int productId);
    List<ProductLike> SearchProductLikeByUserId(Long userId);
    Boolean isProductLike(Long userId, Long productId);
}
