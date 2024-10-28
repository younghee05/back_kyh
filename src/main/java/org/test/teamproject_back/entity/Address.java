package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private Long addressId;
    private Long userId;
    private String address;
    private String detailAddress;
    private int zipCode;
}
