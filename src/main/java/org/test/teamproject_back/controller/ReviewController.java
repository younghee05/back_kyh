package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqReviewDto;
import org.test.teamproject_back.service.ReviewService;

@RestController
@RequestMapping("/user/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<?> addReview(@RequestBody ReqReviewDto dto) {
        reviewService.addReview(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.ok().body(reviewService.getAllReviews());
    }
}
