package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Review;

@Builder
@Data
public class ReqAddReviewDto {
    private Long productId;
    private int rating;
    private String title;
    private String content;

    public Review toReview(Long userId) {
        return Review.builder()
                .userId(userId)
                .productId(productId)
                .rating(rating)
                .title(title)
                .content(content)
                .build();
    }
}
