package org.test.teamproject_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.teamproject_back.entity.Product;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespGraphDataDto {
    private List<Product> products;
}
