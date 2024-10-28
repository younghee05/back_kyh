package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.test.teamproject_back.entity.Address;

@Data
public class ReqAddressDto {
    private String address;
    private String detailAddress;
    private int zipCode;

    public Address toAddress(Long userId) {
        return Address.builder()
                .address(address)
                .userId(userId)
                .detailAddress(detailAddress)
                .zipCode(zipCode)
                .build();
    }
}
