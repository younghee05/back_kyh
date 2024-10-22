package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.Address;

@Mapper
public interface AddressMapper {
    int addAddress(Address address);
    int updateAddress(Address address);
}
