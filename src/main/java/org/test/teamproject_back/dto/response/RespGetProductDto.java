package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespGetProductDto {
    private Long productId;
    private String title;
    private int price;
    private String thumbnailImg;
}
