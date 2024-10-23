package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Review;

@Mapper
public interface ReviewMapper {
    int addReview(Review review);
    Review findReviewByUserId(Long userId);
}
