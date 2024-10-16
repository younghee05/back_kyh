package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddCartDto;
import org.test.teamproject_back.service.CartService;

@RestController
@RequestMapping("/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addCart(@RequestBody ReqAddCartDto dto) {
        cartService.addCart(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCart(@RequestParam Long userId) {
        cartService.searchCart(userId);
        return ResponseEntity.ok().body(null);
    }

}
