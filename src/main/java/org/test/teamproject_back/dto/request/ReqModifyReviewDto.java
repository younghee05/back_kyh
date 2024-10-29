package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Review;

@Builder
@Data
public class ReqModifyReviewDto {
    private int reviewId;
    private int rating;
    private String title;
    private String content;
    private String reviewImg;

    public Review toReview() {
        return Review.builder()
                .reviewId(reviewId)
                .rating(rating)
                .title(title)
                .content(content)
                .reviewImg(reviewImg)
                .build();
    }
}
