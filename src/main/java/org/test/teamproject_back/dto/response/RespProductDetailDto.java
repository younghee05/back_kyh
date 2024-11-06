package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Product;

@Builder
@Data
public class RespProductDetailDto {
    private Product product;
    private Boolean likeCheck;
}
