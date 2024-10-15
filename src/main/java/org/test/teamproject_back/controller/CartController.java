package org.test.teamproject_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/main/cart")
public class CartController {

    @PostMapping("/add")
    public ResponseEntity<?> add () {
        return ResponseEntity.ok().body(null);
    }
}
