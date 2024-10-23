package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private int reviewId;
    private Long userId;
    private Long productId;
    private int rating;
    private String title;
    private String content;
    private String createdAt;
}
