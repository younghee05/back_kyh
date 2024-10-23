package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.response.RespGetProductDto;
import org.test.teamproject_back.dto.response.RespProductDetailDto;
import org.test.teamproject_back.dto.response.RespSearchProductDto;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.repository.ProductMapper;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public RespSearchProductDto getAllProducts() {
        List<Product> productList = productMapper.findAllProductsList();
        int productCount = productMapper.findAllProductCount();

        return RespSearchProductDto.builder()
                .products(productList)
                .count(productCount)
                .build();
    }

    public RespSearchProductDto searchProducts(String title) {
        List<Product> productList = productMapper.findProductByTitle(title.trim());
        int productCount = productMapper.findProductCountByTitle(title.trim());

        return RespSearchProductDto.builder()
                .products(productList)
                .count(productCount)
                .build();
    }

    public RespSearchProductDto searchCategory(int categoryId) {
        List<Product> productList = productMapper.findProductByCategory(categoryId);
        int productCount = productMapper.findProductCountByCategory(categoryId);

        return RespSearchProductDto.builder()
                .products(productList)
                .count(productCount)
                .build();
    }

    public RespSearchProductDto searchSemiCategory(int categoryId, int semiCategoryId) {
        List<Product> productList = productMapper.findProductBySemiCategory(categoryId, semiCategoryId);
        int productCount = productMapper.findProductCountBySemiCategory(categoryId, semiCategoryId);

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
        List<Product> productList = productMapper.findNewProduct();

        return productList;
    }

    public List<Product> getPopularityProduct() {
        List<Product> productList = productMapper.findPopularityProduct();

        return productList;
    }
}
