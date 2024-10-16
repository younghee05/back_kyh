package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ReqAddGuestCartDto {
    private Long productId;
    private int quantity;
    private int price;

}
