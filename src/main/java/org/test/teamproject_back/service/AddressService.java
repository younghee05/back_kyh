package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqAddressDto;
import org.test.teamproject_back.repository.AddressMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;


    public void addAddress(ReqAddressDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        addressMapper.addAddress(dto.toAddress(principalUser.getId()));
    }

    public void deleteAddress(Long addressId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        addressMapper.deleteAddress(addressId, principalUser.getId());
    }
}
