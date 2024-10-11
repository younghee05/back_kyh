package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.dto.request.ReqSigninDto;
import org.test.teamproject_back.dto.request.ReqSignupDto;
import org.test.teamproject_back.exception.SignupException;
import org.test.teamproject_back.service.AdminService;
import org.test.teamproject_back.service.AuthService;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody ReqSignupDto dto) throws SignupException {
        String roleName = "ROLE_MANAGER";
        return ResponseEntity.ok().body(authService.signup(dto, roleName));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody ReqSigninDto dto) {
        return ResponseEntity.ok().body(authService.signin(dto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllAdminUsers(@RequestParam(required = false) String role) {
        return ResponseEntity.ok().body(adminService.getAllUsers(role));
    }

    @DeleteMapping("/delete/{id}") 
    public ResponseEntity<?> delete(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<?> modify(@Valid @RequestBody ReqModifyUserDto dto)  {
//        System.out.println(dto);
        adminService.modifyUser(dto);
        return ResponseEntity.ok().body(true);
    }
}
