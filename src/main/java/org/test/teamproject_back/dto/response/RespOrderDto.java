package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Order;

@Builder
@Data
public class RespOrderDto {
    private Order order;
}
