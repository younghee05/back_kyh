package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqDeleteCheckDto;
import org.test.teamproject_back.dto.request.ReqModifyAdminUserDto;
import org.test.teamproject_back.dto.request.ReqSearchUserDto;
import org.test.teamproject_back.dto.request.ReqUserDto;
import org.test.teamproject_back.service.AdminUserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers(ReqUserDto dto) {
        return ResponseEntity.ok().body(adminUserService.getAllUsers(dto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(ReqSearchUserDto dto) {
        return ResponseEntity.ok().body(adminUserService.searchUsers(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@RequestBody ReqDeleteCheckDto dto) {
        adminUserService.deleteUser(dto);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> modify(@Valid @RequestBody ReqModifyAdminUserDto dto)  {
        System.out.println(dto);
        adminUserService.modifyUser(dto);
        return ResponseEntity.ok().body(true);
    }
}
