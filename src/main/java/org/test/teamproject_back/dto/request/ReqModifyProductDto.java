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
    private String title;
    private int price;
    private int stock;
    private String description;
    private String origin;
    private String thumbnailImg;
    private List<String> contentsImg;
    private int categoryId;
    private int semiCategoryId;

    public Product toProduct(Long productId, String contentsImg1, String contentsImg2, String contentsImg3, String contentsImg4) {
        return Product.builder()
                .productId(productId)
                .title(title)
                .price(price)
                .stock(stock)
                .description(description)
                .origin(origin)
                .thumbnailImg(thumbnailImg)
                .contentsImg1(contentsImg1)
                .contentsImg2(contentsImg2)
                .contentsImg3(contentsImg3)
                .contentsImg4(contentsImg4)
                .build();
    }

    public ProductCategory toProductCategory(Long producntId) {
        return ProductCategory.builder()
                .categoryId(categoryId)
                .semiCategoryId(semiCategoryId)
                .productId(producntId)
                .build();
    }
}
