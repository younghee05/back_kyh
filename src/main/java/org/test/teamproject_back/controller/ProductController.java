package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/") // 전체
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/search") // 상품 조회
    public ResponseEntity<?> searchProduct(@RequestParam String title) {
        return ResponseEntity.ok().body(productService.searchProducts(title));
    }

}
