package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqDeleteCheckDto;
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
        String[] images = dto.getContentsImg().stream()
                .toArray(String[]::new);

        String img1 = images.length > 0 ? images[0] : null;
        String img2 = images.length > 1 ? images[1] : null;
        String img3 = images.length > 2 ? images[2] : null;
        String img4 = images.length > 3 ? images[3] : null;

        System.out.println(img1);
        System.out.println(img2);
        System.out.println(img3);
        System.out.println(img4);

        Product product = dto.toProduct(img1, img2, img3, img4);
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
    public void deleteProduct(ReqDeleteCheckDto dto) {
        adminProductMapper.deleteProductById(dto.getCheckedIds());
        adminProductMapper.deleteProductCategoryById(dto.getCheckedIds());
    }
}
