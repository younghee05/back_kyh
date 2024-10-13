package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;

import java.util.List;
import java.util.Set;

@Builder
@Data
public class ReqModifyProductDto {
    private Long productId;
    private String title;
    private int price;
    private int stock;
    private String description;
    private String origin;
    private String img;
    private int categoryId;

    public Product toProduct() {
        return Product.builder()
                .productId(productId)
                .title(title)
                .price(price)
                .stock(stock)
                .description(description)
                .origin(origin)
                .img(img)
                .build();
    }
}
