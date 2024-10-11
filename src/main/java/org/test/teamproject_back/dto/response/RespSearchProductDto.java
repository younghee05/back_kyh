package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Category;

import java.util.Date;
import java.util.Set;

@Builder
@Data
public class RespSearchProductDto {
    private Set<Category> category;
    private String title;
    private int price;
    private String origin;
    private int salesCount;
    private int stock;
    private String createdDate;
}
