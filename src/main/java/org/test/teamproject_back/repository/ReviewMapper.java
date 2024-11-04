package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReviewMapper {
    int addReview(Review review);
    List<Review> findMyReviewByUserId(Long userId);
    List<Review> findAllReview();
    LocalDate findReviewDate(int reviewId);
    int updateReview(Review review);
    int deleteReview(List<Long> checkedIds);
}
