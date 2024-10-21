package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.entity.ProductCategory;

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
    private String thumbnailImg;
    private String contentsImg;
    private int categoryId;
    private int semiCategoryId;

    public Product toProduct() {
        return Product.builder()
                .productId(productId)
                .title(title)
                .price(price)
                .stock(stock)
                .description(description)
                .origin(origin)
                .thumbnailImg(thumbnailImg)
                .contentsImg(contentsImg)
                .build();
    }

    public ProductCategory toProductCategory() {
        return ProductCategory.builder()
                .categoryId(categoryId)
                .semiCategoryId(semiCategoryId)
                .productId(productId)
                .build();
    }
}
