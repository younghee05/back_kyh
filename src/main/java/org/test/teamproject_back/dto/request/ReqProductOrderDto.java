package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.test.teamproject_back.entity.Address;

@Data
public class ReqProductOrderDto {
    private Long productId;
    private String address;
    private String detailAddress;
    private String zipCode;

    public Address toAddress(Long userId) {
        return Address.builder()
                .userId(userId)
                .address(address)
                .detailAddress(detailAddress)
                .zipCode(zipCode)
                .build();
    }
}
