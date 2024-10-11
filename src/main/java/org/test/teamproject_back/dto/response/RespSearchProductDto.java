package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class RespSearchProductDto {
    private String category;
    private String title;
    private int price;
    private String origin;
    private int salesCount;
    private int stock;
    private String createdDate;
}
