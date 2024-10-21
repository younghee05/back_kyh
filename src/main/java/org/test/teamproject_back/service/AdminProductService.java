package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.entity.ProductCategory;
import org.test.teamproject_back.repository.AdminProductMapper;
import org.test.teamproject_back.repository.ProductMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class AdminProductService {

    @Autowired
    private AdminProductMapper adminProductMapper;

    @Transactional(rollbackFor = SQLException.class)
    public void addProduct(ReqAddProductDto dto) {
        Product product = dto.toProduct();
        adminProductMapper.addProduct(product);

        ProductCategory productCategory = ProductCategory.builder()
                .productId(product.getProductId())
                .categoryId(dto.getCategoryId())
                .semiCategoryId(dto.getSemiCategory())
                .build();
        adminProductMapper.addProductCategory(productCategory);
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyProduct(ReqModifyProductDto dto) {
        adminProductMapper.updateProduct(dto.toProduct());
        adminProductMapper.updateProductCategory(dto.toProductCategory());
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deleteProduct(Long id) {
        adminProductMapper.deleteProductById(id);
        adminProductMapper.deleteProductCategoryById(id);
    }
}
