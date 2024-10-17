package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.service.ProductService;

@RestController
@RequestMapping("/admin/main/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody ReqAddProductDto dto) {
        System.out.println(dto);
        productService.addProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 전체 불러오기 기능

    @GetMapping("/search") // 상품 조회
    public ResponseEntity<?> searchProduct(@RequestParam String title) {
        return ResponseEntity.ok().body(productService.searchProduct(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyProduct(@RequestBody ReqModifyProductDto dto) {
        productService.modifyProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(true);
    }

}
