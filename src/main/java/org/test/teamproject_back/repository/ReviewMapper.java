package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface ReviewMapper {
    int addReview(Review review);
    Review findReviewByUserId(Long userId);
    LocalDate findReviewDate(int reviewId);
    int updateReview(Review review);
    int deleteReview(int reviewId);
}
