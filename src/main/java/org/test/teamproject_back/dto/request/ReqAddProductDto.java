package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.test.teamproject_back.entity.Product;

import java.util.List;
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
    private List<String> contentsImg;

    public Product toProduct(String contentsImg1, String contentsImg2, String contentsImg3, String contentsImg4) {
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
