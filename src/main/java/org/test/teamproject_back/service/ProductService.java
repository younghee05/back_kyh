package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(rollbackFor = SQLException.class)
    public void addProduct(ReqAddProductDto dto) {
        Product product = Product.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .description(dto.getDescription())
                .origin(dto.getOrigin())
                .img(dto.getImg())
                .build();

        productMapper.addProduct(product);

        ProductCategory productCategory = ProductCategory.builder()
                .productId(product.getProductId())
                .categoryId(dto.getCategoryId())
                .build();
        productMapper.addProductCategory(productCategory);
    }

    public RespSearchProductDto searchProduct(String title) {

        List<Product> response = productMapper.findProductByTitle(title.trim());

        if (response == null || response.isEmpty()) {
            throw new InvalidInputException("해당 상품 정보가 존재하지 않습니다.");
        }

        return RespSearchProductDto.builder()
                .products(response)
                .build();
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deleteProduct(Long id) {
        if(!(Optional.ofNullable(productMapper.findProductById(id))).isPresent()) {
            throw new InvalidInputException("해당 상품 정보가 존재하지 않습니다.");
        }
        productMapper.deleteProductById(id);
        productMapper.deleteProductCategoryById(id);
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyProduct(ReqModifyProductDto dto) {
        if(!(Optional.ofNullable(productMapper.findProductById(dto.getProductId()))).isPresent()) {
            throw new InvalidInputException("해당 상품 정보가 존재하지 않습니다.");
        }
        productMapper.updateProduct(dto.toProduct());
        productMapper.updateProductCategory(dto.getCategoryId());
    }

}
