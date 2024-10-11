package org.test.teamproject_back.dto.request;

import lombok.Data;

@Data
public class ReqAddProductDto {
    private String title;
    private int price;
    private int stock;
    private int categoryId;
    private String origin;
    private String img;
}
