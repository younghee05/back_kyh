package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqImgDto;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.exception.SignupException;
import org.test.teamproject_back.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUserInfo() {
        return ResponseEntity.ok().body(userService.getUserInfo());
    }

    // 비밀번호, 주소, 이메일 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyUserInfo(@RequestBody ReqModifyUserDto dto) throws SignupException {
        userService.modifyUserInfo(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/img")
    public ResponseEntity<?> modifyImgProfile(@RequestBody ReqImgDto dto) {
        userService.modifyImgProfile(dto);
        return ResponseEntity.ok().body(true);
    }
}
