package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.service.ProductService;

@RestController
@RequestMapping("/user/public/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("") // 전체
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/search") // 상품 조회
    public ResponseEntity<?> searchProduct(@RequestParam(required = false) String title) {
        return ResponseEntity.ok().body(productService.searchProducts(title));
    }

    @GetMapping("/category")
    public ResponseEntity<?> searchCategory(@RequestParam(required = false) int main
            , @RequestParam(required = false, defaultValue = "0") int sub) {
        if (sub == 0) {
            return ResponseEntity.ok().body(productService.searchCategory(main));
        }
        return ResponseEntity.ok().body(productService.searchSemiCategory(main, sub));
    }

    // 상품 디테일 페이지
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.ok().body(productService.getProductDetail(productId));
    }

}
