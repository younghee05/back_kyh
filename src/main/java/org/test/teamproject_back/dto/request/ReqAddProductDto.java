package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.test.teamproject_back.entity.Product;

import java.util.Set;

@Data
public class ReqAddProductDto {
    private String title;
    private int price;
    private int stock;
    private int categoryId;
    private int semiCategory;
    private String description;
    private String origin;
    private String thumbnailImg;
    private String contentsImg1;
    private String contentsImg2;
    private String contentsImg3;
    private String contentsImg4;

    public Product toProduct() {
        return Product.builder()
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
}
