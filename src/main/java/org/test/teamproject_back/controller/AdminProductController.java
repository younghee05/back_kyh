package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqDeleteCheckDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.dto.request.ReqSearchDto;
import org.test.teamproject_back.service.AdminProductService;
import org.test.teamproject_back.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private AdminProductService adminProductService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@Valid  @RequestBody ReqAddProductDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        adminProductService.addProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(ReqSearchDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(productService.getAllProducts(dto));
    }

    @GetMapping("/search") // 상품 조회
    public ResponseEntity<?> searchProducts(ReqSearchDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(productService.searchProducts(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> modifyProduct(@RequestBody ReqModifyProductDto dto) {
        System.out.println(dto);
        adminProductService.modifyProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteProduct(@RequestBody ReqDeleteCheckDto dto) {
        System.out.println(dto);
        adminProductService.deleteProduct(dto);
        return ResponseEntity.ok().body(true);
    }

}
