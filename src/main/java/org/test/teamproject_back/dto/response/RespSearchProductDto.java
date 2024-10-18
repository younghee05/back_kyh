package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Data
public class RespSearchProductDto {
    private List<Product> products;
    private int count;
}
