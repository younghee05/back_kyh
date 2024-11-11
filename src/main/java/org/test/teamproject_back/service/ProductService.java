package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqSearchDto;
import org.test.teamproject_back.dto.response.RespProductDetailDto;
import org.test.teamproject_back.dto.response.RespSearchProductDto;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.repository.ProductLikeMapper;
import org.test.teamproject_back.repository.ProductMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductLikeMapper productLikeMapper;

    public RespSearchProductDto getAllProducts(ReqSearchDto dto) {
        int startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> paging = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit()
        );

        List<Product> productList = productMapper.findAllProductsList(startIndex, dto.getLimit());
        System.out.println(productList);
        int productCount = productMapper.findAllProductCount();

        return RespSearchProductDto.builder()
                .products(productList)
                .count(productCount)
                .build();
    }

    public RespSearchProductDto searchProducts(ReqSearchDto dto) {
        int startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> paging = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "keyword", dto.getKeyword() == null ? "" : dto.getKeyword()
        );

        List<Product> productList = productMapper.findProduct(paging);
        int productCount = productMapper.findProductCountByTitle(dto.getKeyword());

        return RespSearchProductDto.builder()
                .products(productList)
                .count(productCount)
                .build();
    }

    public RespSearchProductDto searchCategory(ReqSearchDto dto) {
        int startIndex = (dto.getPage() - 1) * dto.getLimit();
        int mainCategoryId = Integer.parseInt(dto.getCategoryId().substring(0, 2));
        int semiCategoryId = 0;

        if (dto.getCategoryId().length() == 4) {
            semiCategoryId = Integer.parseInt((dto.getCategoryId()).substring(2, 4));
        }

        Map<String, Object> paging = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "mainCategoryId", mainCategoryId,
                "semiCategoryId", semiCategoryId
        );

        System.out.println(startIndex);
        List<Product> productList = productMapper.findProductByCategory(paging);
        int productCount = productMapper.findProductCountBySemiCategory(mainCategoryId, semiCategoryId);

        return RespSearchProductDto.builder()
                .products(productList)
                .count(productCount)
                .build();

    }

    public RespProductDetailDto getProductDetail(Long productId) {
        Long userId = 0l;

        Authentication authentication = SecurityContextHolder
        .getContext()
        .getAuthentication();

        if(!authentication.getPrincipal().toString().equals("anonymousUser")) {
            PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
            userId = principalUser.getId();
        }

        Product product = productMapper.findProductById(productId);
        Boolean likeCheck = productLikeMapper.isProductLike(userId, productId);

        return RespProductDetailDto.builder()
                .product(product)
                .likeCheck(likeCheck)
                .build();
    }

    public List<Product> getNewProduct() {
        return productMapper.findNewProduct();
    }

    public List<Product> getPopularityProduct() {
        return productMapper.findPopularityProduct();
    }

    public List<Product> getRecommendedProduct() {
        return productMapper.findRecommendedProduct();
    }
}
