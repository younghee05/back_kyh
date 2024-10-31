package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqSearchDto;
import org.test.teamproject_back.dto.response.RespProductDetailDto;
import org.test.teamproject_back.dto.response.RespSearchProductDto;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.repository.ProductMapper;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public RespSearchProductDto getAllProducts(ReqSearchDto dto) {
        int startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> paging = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit()
        );
        List<Product> productList = productMapper.findAllProductsList(paging);
        int productCount = productMapper.findAllProductCount();
        productList.removeIf(Objects::isNull);

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
                "searchTitle", dto.getTitle() == null ? "" : dto.getTitle()
        );

        List<Product> productList = productMapper.findProductByTitle(paging);
        int productCount = productMapper.findProductCountByTitle(dto.getTitle());

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

        List<Product> productList = productMapper.findProductByCategory(paging);
        int productCount = productMapper.findProductCountBySemiCategory(mainCategoryId, semiCategoryId);

        return RespSearchProductDto.builder()
                .products(productList)
                .count(productCount)
                .build();

    }

    public RespProductDetailDto getProductDetail(Long productId) {
        Product product = productMapper.findProductById(productId);

        return RespProductDetailDto.builder()
                .product(product)
                .build();
    }

    public List<Product> getNewProduct() {
        return productMapper.findNewProduct();
    }

    public List<Product> getPopularityProduct() {
        return productMapper.findPopularityProduct();
    }
}
