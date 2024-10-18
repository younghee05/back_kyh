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
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/main/search") // 상품 조회
    public ResponseEntity<?> searchProduct(@RequestParam(required = false) String title) {
        return ResponseEntity.ok().body(productService.searchProducts(title));
    }

    @GetMapping("/main/cate") //
    public ResponseEntity<?> searchCategory(@RequestParam(required = false) Long cate) {
        return ResponseEntity.ok().body(productService.searchCategory(cate));
    }

    @GetMapping("/main")
    public ResponseEntity<?> searchSemiCategory(@RequestParam(required = false) Long cate
            , @RequestParam(required = false) Long id) {
        return ResponseEntity.ok().body(productService.searchSemiCategory(cate, id));
    }

}
