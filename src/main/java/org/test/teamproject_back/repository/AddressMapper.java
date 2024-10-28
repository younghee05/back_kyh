package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Address;
import org.test.teamproject_back.entity.Cart;

import java.util.List;

@Mapper
public interface AddressMapper {
    int addAddress(Address address);
    int updateAddress(Address address);
    int deleteAddress(Long addressId, Long userId);
    Address findAddressByUserId(Long userId);
}

