package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.service.ProductService;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ReqAddProductDto dto) {
        return ResponseEntity.ok().body(productService.addProduct(dto));
    }

    @GetMapping("/search") // 상품 조회
    public ResponseEntity<?> searchProduct(@RequestParam String title) {
        return ResponseEntity.ok().body(productService.searchProduct(title));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<?> modifyProduct(@RequestBody ReqModifyProductDto dto) {
//        System.out.println(dto);
        productService.modifyProduct(dto);
        return ResponseEntity.ok().body(true);
    }
}
