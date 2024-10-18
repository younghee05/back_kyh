package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.test.teamproject_back.entity.Product;

import java.util.Set;

@Data
public class ReqAddProductDto {
    private String title;
    private int price;
    private int stock;
    private int category;
    private int semiCategory;
    private String description;
    private String origin;
    private String thumbnailImg;
    private String contentsImg;

    public Product toProduct(String thumbnailImg) {
        return Product.builder()
                .title(title)
                .price(price)
                .stock(stock)
                .description(description)
                .origin(origin)
                .thumbnailImg(thumbnailImg)
                .contentsImg(contentsImg)
                .build();
    }
}
