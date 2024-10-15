package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddCartDto;
import org.test.teamproject_back.dto.request.ReqAddGuestCartDto;
import org.test.teamproject_back.entity.CartItem;
import org.test.teamproject_back.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        return ResponseEntity.ok().body(null);
    }

}
