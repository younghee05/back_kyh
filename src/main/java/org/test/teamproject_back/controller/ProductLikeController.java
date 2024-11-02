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

    @PostMapping("/like/{id}")
    public ResponseEntity<?> productLike(@PathVariable Long id) {
        productLikeService.productLike(id);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/dislike/{id}")
    public ResponseEntity<?> productDislike(@PathVariable Long id) {
        productLikeService.productDislike(id);
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
