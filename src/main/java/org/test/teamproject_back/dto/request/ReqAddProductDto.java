package org.test.teamproject_back.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class ReqAddProductDto {
    private String title;
    private int price;
    private int stock;
    private int categoryId;
    private String origin;
    private String img;
}
