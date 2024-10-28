package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqAddressDto;
import org.test.teamproject_back.service.AddressService;

@RequestMapping("/user/address")
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("")
    public ResponseEntity<?> addAddress(@RequestBody ReqAddressDto dto) {
        addressService.addAddress(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok().body(true);
    }
}
