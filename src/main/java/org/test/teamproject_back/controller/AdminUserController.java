package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.service.AdminUserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) int role) {
        return ResponseEntity.ok().body(adminUserService.getAllUsers(role));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(required = false) int role, @RequestParam(required = false) String name) {
        return ResponseEntity.ok().body(adminUserService.searchUsers(role, name));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        adminUserService.deleteUser(id);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> modify(@Valid @RequestBody ReqModifyUserDto dto)  {
        adminUserService.modifyUser(dto);
        return ResponseEntity.ok().body(true);
    }
}
