package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReqDeleteCartDto {
    private Long userId;
    private Long cartId;
    private Long cartItemId;
}
