package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.dto.response.RespSearchProductDto;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.entity.ProductCategory;
import org.test.teamproject_back.exception.InvalidInputException;
import org.test.teamproject_back.repository.ProductMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Value("${user.profile.thumbnailImg.default}")
    private String defaultThumbnailImg;

    @Transactional(rollbackFor = SQLException.class)
    public void addProduct(ReqAddProductDto dto) {
        Product product = null;

        if (dto.getThumbnailImg().isBlank() || dto.getThumbnailImg() == null) {
            product = dto.toProduct(defaultThumbnailImg);
            productMapper.addProduct(product);
        } else {
            product = dto.toProduct(dto.getThumbnailImg());
            productMapper.addProduct(product);
        }

        ProductCategory productCategory = ProductCategory.builder()
                .productId(product.getProductId())
                .categoryId(dto.getCategory())
                .semiCategoryId(dto.getSemiCategory())
                .build();
        productMapper.addProductCategory(productCategory);
    }

    public RespSearchProductDto getAllProducts() {
        List<Product> productList = productMapper.getAllProductsList();

        return RespSearchProductDto.builder()
                .products(productList)
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

    public RespSearchProductDto searchCategory(Long categoryId) {
        List<Product> productList = productMapper.findProductByCategory(categoryId);

        return RespSearchProductDto.builder()
                .products(productList)
                .build();
    }

    public RespSearchProductDto searchSemiCategory(Long categoryId, Long semiCategoryId) {
        System.out.println(categoryId + " " + semiCategoryId);
        List<Product> productList = productMapper.findProductBySemiCategory(categoryId, semiCategoryId);

        return RespSearchProductDto.builder()
                .products(productList)
                .build();
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deleteProduct(Long id) {
        productMapper.deleteProductById(id);
        productMapper.deleteProductCategoryById(id);
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyProduct(ReqModifyProductDto dto) {
        productMapper.updateProduct(dto.toProduct());
        productMapper.updateProductCategory(dto.getCategoryId());
    }

}
