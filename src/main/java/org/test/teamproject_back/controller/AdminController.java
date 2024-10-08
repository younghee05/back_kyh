package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.teamproject_back.dto.request.ReqSigninDto;
import org.test.teamproject_back.service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody ReqSigninDto dto) {
        return ResponseEntity.ok().body(null);
    }
}
