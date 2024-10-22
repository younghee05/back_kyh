package org.test.teamproject_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.test.teamproject_back.dto.request.ReqAddressDto;

@RestController
public class AddressController {

    @PostMapping("/address")
    public ResponseEntity<?> addAddress(@RequestBody ReqAddressDto dto) {

    }
}
