package org.test.teamproject_back.dto.request;

import lombok.Data;

@Data
public class ReqAddProductDto {
    private String title;
    private long price;
    private long stock;
    private int categoryId;
    private String origin;
    private String img;
}
