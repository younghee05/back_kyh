package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqModifyReviewDto;
import org.test.teamproject_back.dto.request.ReqAddReviewDto;
import org.test.teamproject_back.repository.ReviewMapper;
import org.test.teamproject_back.service.ReviewService;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/user/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewMapper reviewMapper;

    @PostMapping("")
    public ResponseEntity<?> addReview(@RequestBody ReqAddReviewDto dto) {
        reviewService.addReview(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.ok().body(reviewService.getAllReviews());
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkDate(@RequestParam int id) {
        LocalDate reviewDate = reviewMapper.findReviewDate(id);
        Period period = Period.between(reviewDate, LocalDate.now());

        if(period.getDays() > 7) {
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyReview(@RequestBody ReqModifyReviewDto dto) {
        reviewService.modifyReview(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().body(true);
    }
}
