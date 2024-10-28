package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.dto.request.ReqSearchDto;
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
    public ResponseEntity<?> searchProduct(ReqSearchDto dto) {
        return ResponseEntity.ok().body(productService.searchProducts(dto));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> searchCategory(ReqSearchDto dto) {
        if ((dto.getCategoryId()).length() == 2) {
            System.out.println(dto.getCategoryId());
            return ResponseEntity.ok().body(productService.searchCategory(dto));
        }
        return ResponseEntity.ok().body(productService.searchSemiCategory(dto));
    }

    // 상품 디테일 페이지
    @GetMapping("/product/detail")
    public ResponseEntity<?> getProductDetail(@RequestParam Long productId) {
        return ResponseEntity.ok().body(productService.getProductDetail(productId));
    }

    // 신상품(최근 등록순)
    @GetMapping("/new")
    public ResponseEntity<?> getNewProduct() {
        return ResponseEntity.ok().body(productService.getNewProduct());
    }

    // 인기상품(판매량순)
    @GetMapping("/best")
    public ResponseEntity<?> getPopularityProduct() {
        return ResponseEntity.ok().body(productService.getPopularityProduct());
    }
}
