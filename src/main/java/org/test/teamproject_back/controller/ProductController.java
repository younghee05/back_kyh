package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.service.ProductService;

@RestController
@RequestMapping("/user/public")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product") // 전체
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/product/search") // 상품 조회
    public ResponseEntity<?> searchProduct(@RequestParam(required = false) String title) {
        return ResponseEntity.ok().body(productService.searchProducts(title));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> searchCategory(@RequestParam(required = false) int main
            , @RequestParam(required = false, defaultValue = "0") int sub) {
        if (sub == 0) {
            return ResponseEntity.ok().body(productService.searchCategory(main));
        }
        return ResponseEntity.ok().body(productService.searchSemiCategory(main, sub));
    }

    // 상품 디테일 페이지
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.ok().body(productService.getProductDetail(productId));
    }

    // 신상품(최근 등록순)
    @GetMapping("/new")
    public ResponseEntity<?> getNewProduct() {
        return ResponseEntity.ok().body(productService.getNewProduct());
    }

    // 인기상품(판매량순)
    @GetMapping("/popularity")
    public ResponseEntity<?> getPopularityProduct() {
        return ResponseEntity.ok().body(productService.getPopularityProduct());
    }
}
