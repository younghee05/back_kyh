package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqCertificationDto;
import org.test.teamproject_back.dto.request.ReqSendMailDto;
import org.test.teamproject_back.service.EmailService;

@RestController
@RequestMapping("public/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("")
    public ResponseEntity<?> sendEmail(@RequestBody ReqSendMailDto dto) throws Exception {
        System.out.println(dto);
        emailService.sendEmail(dto);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authEmail(@RequestBody ReqCertificationDto dto) {
        return ResponseEntity.ok().body(emailService.authEmail(dto.getCheckNum()));
    }
}
