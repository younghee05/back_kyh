package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.response.RespSearchProductDto;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.repository.ProductMapper;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public int addProduct(ReqAddProductDto dto) {
        Product product = Product.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .categoryId(dto.getCategoryId())
                .origin(dto.getOrigin())
                .img(dto.getImg())
                .build();
        return productMapper.addProduct(product);
    }

    public ResponseEntity<?> searchProduct(String title) {
        Product response = productMapper.searchProductByTitle(title);
        RespSearchProductDto search = RespSearchProductDto.builder()
                .title(response.getTitle())

                .build();
    }

}
