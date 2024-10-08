package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Long productId;
    private String title;
    private Long price;
    private Long stock;
    private Long salesCount;
    private int categoryId;
    private String description;
    private String origin;
    private String img;
    private String createdDate;
}
