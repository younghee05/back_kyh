package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Review;

@Builder
@Data
public class ReqModifyReviewDto {
    private Long reviewId;
    private int rating;
    private String title;
    private String content;
    private String reviewImg;
}
