package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.test.teamproject_back.entity.Product;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
public class ReqAddProductDto {
    @NotBlank(message = "상품명은 공백일 수 없습니다.")
    private String title;
    @NotBlank(message = "가격은 공백일 수 없습니다.")
    private int price;
    @NotBlank(message = "재고는 공백일 수 없습니다.")
    private int stock;
    @NotBlank(message = "설명은 공백일 수 없습니다.")
    private String description;
    @NotBlank(message = "원산지는 공백일 수 없습니다.")
    private String origin;
    @NotBlank(message = "카테고리는 공백일 수 없습니다.")
    private int categoryId;
    private int semiCategoryId;
    @NotBlank(message = "이미지는 공백일 수 없습니다.")
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
