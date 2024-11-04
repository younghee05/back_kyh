package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqDeleteCheckDto;
import org.test.teamproject_back.dto.request.ReqDeleteReviewDto;
import org.test.teamproject_back.dto.request.ReqModifyReviewDto;
import org.test.teamproject_back.dto.request.ReqAddReviewDto;
import org.test.teamproject_back.repository.ReviewMapper;
import org.test.teamproject_back.service.ReviewService;

import java.time.LocalDate;
import java.time.Period;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewMapper reviewMapper;

    @PostMapping("/user/review")
    public ResponseEntity<?> addReview(@RequestBody ReqAddReviewDto dto) {
        reviewService.addReview(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/user/review")
    public ResponseEntity<?> getAllMyReviews() { // 마이 페이지 리뷰
        return ResponseEntity.ok().body(reviewService.getAllMyReviews());
    }

    @GetMapping("/user/public/review")
    public ResponseEntity<?> getAllReviews() { // 전체 페이지 리뷰
        return ResponseEntity.ok().body(reviewService.getAllReviews());
    }

    @GetMapping("/user/review/check")
    public ResponseEntity<?> checkDate(@RequestParam int id) {
        LocalDate reviewDate = reviewMapper.findReviewDate(id);
        Period period = Period.between(reviewDate, LocalDate.now());

        if(period.getDays() > 7) {
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/user/review/{id}")
    public ResponseEntity<?> modifyReview(@RequestBody ReqModifyReviewDto dto) {
        reviewService.modifyReview(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/user/review")
    public ResponseEntity<?> deleteReview(@RequestBody ReqDeleteReviewDto dto) {
        reviewService.deleteReview(dto);
        return ResponseEntity.ok().body(true);
    }
}
