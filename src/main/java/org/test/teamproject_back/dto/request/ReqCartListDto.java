package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.test.teamproject_back.entity.Address;

import java.util.List;

@Data
public class ReqCartListDto {
    private List<Long> id;
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
