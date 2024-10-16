package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespSearchCartDto {
    private Long productId;
    private String thumbnailImg;
    private String title;
    private String description;
    private int price;
    private int quantity;
    private int totalPrice;
    private String createdAt;
}
