package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReqAddressDto {
    private String address;
    private String detailAddress;
}
