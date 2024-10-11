package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;

import java.util.Set;

@Builder
@Data
public class ReqModifyProductDto {
    private Long productId;
    private String title;
    private int price;
    private int stock;
    private int salesCount;
    private int categoryId;
    private String description;
    private String origin;
    private String img;

    public Product toEntity(Category category) {
        return Product.builder()
                .productId(productId)
                .title(title)
                .price(price)
                .stock(stock)
                .salesCount(salesCount)
                .category(category)
                .description(description)
                .origin(origin)
                .img(img)
                .build();
    }
}
