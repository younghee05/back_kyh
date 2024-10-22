package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.service.ProductLikeService;

@RestController
@RequestMapping("/user/product")
public class ProductLikeController {
    @Autowired
    private ProductLikeService productLikeService;

    @PostMapping("/like/{productId}")
    public ResponseEntity<?> productLike(@PathVariable Long productId) {
        productLikeService.productLike(productId);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/dislike/{productId}")
    public ResponseEntity<?> productDislike(@PathVariable Long productId) {
        productLikeService.productDislike(productId);
        return ResponseEntity.ok().body(true);
    }

//    @GetMapping("/{productId}")
//    public ResponseEntity<?> getLikeCount(@PathVariable int productId) {
//        return ResponseEntity.ok().body(productLikeService.likeCount(productId));
//    }

    @GetMapping("")
    public ResponseEntity<?> getWishList() {
        return ResponseEntity.ok().body(productLikeService.getWishList());
    }
}
