package org.test.teamproject_back.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqCartListDto {
    private List<Long> cartItemId;
}
