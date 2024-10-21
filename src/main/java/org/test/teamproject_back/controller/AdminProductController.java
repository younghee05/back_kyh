package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.service.AdminProductService;
import org.test.teamproject_back.service.ProductService;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private AdminProductService adminProductService;

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody ReqAddProductDto dto) {
        adminProductService.addProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/search") // 상품 조회
    public ResponseEntity<?> searchProducts(@RequestParam String title) {
        return ResponseEntity.ok().body(productService.searchProducts(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyProduct(@RequestBody ReqModifyProductDto dto) {
        adminProductService.modifyProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        adminProductService.deleteProduct(id);
        return ResponseEntity.ok().body(true);
    }

}
