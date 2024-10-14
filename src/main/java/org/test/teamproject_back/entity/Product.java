package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Long productId;
    private String title;
    private int price;
    private int stock;
    private int salesCount;
    private String description;
    private String origin;
    private String thumbnailImg;
    private String contentsImg;
    private String createdDate;
    private Set<ProductCategory> productCategories;
}
