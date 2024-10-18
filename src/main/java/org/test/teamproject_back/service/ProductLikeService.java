package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.entity.ProductLike;
import org.test.teamproject_back.repository.ProductLikeMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

@Service
public class ProductLikeService {
    @Autowired
    private ProductLikeMapper productLikeMapper;

    public void productLike(Long productId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();


        ProductLike productLike = ProductLike.builder()
                .productId(productId)
                .userId(principalUser.getId())
                .build();
        productLikeMapper.addProductLike(productLike);
    }

    public int likeCount(int productId) {
        return productLikeMapper.getLikeCountByProductId(productId);
    }

    public void productDislike(Long productId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        ProductLike productDisLike = ProductLike.builder()
                .productId(productId)
                .userId(principalUser.getId())
                .build();
        productLikeMapper.deleteProductLike(productDisLike);
    }

    public ResponseEntity<?> getWishList() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(productLikeMapper.SearchProductLikeByUserId(principalUser.getId()));
    }
}
