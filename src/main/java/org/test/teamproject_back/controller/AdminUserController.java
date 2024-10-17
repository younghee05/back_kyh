package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.service.AdminUserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin/main")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/role")
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String role) {
        return ResponseEntity.ok().body(adminUserService.getAllUsers(role));
    }

    // 사용자 정보로 user 검색 기능

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        adminUserService.deleteUser(id);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@Valid @RequestBody ReqModifyUserDto dto)  {
        adminUserService.modifyUser(dto);
        return ResponseEntity.ok().body(true);
    }
}
