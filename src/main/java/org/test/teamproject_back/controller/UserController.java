package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        System.out.println(dto);
        userService.modifyUserInfo(dto);
        return ResponseEntity.ok().body(true);
    }
}
