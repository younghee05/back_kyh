package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqSigninDto;
import org.test.teamproject_back.dto.request.ReqSignupDto;
import org.test.teamproject_back.exception.SignupException;
import org.test.teamproject_back.service.AdminUserService;
import org.test.teamproject_back.service.AuthService;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody ReqSignupDto dto) throws SignupException {
        String roleName = "ROLE_MANAGER";
        return ResponseEntity.ok().body(authService.signup(dto, roleName));
    }

    @PostMapping("")
    public ResponseEntity<?> signin(@Valid @RequestBody ReqSigninDto dto) {
        return ResponseEntity.ok().body(authService.signin(dto));
    }
}
