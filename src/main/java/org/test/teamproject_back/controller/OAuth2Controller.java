package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.teamproject_back.dto.request.ReqOAuth2MergeDto;
import org.test.teamproject_back.entity.OAuth2User;
import org.test.teamproject_back.service.AuthService;
import org.test.teamproject_back.service.OAuth2Service;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/public")
public class OAuth2Controller {

    @Autowired
    private OAuth2Service oAuth2Service;
    @Autowired
    private AuthService authService;

    @PostMapping("/oauth2/signin")
    public ResponseEntity<?> signin(@Valid ReqOAuth2MergeDto dto , BindingResult bindingResult) {
        OAuth2User oAuth2User = authService.mergeSignin(dto);
        oAuth2Service.merge(oAuth2User);
        return ResponseEntity.ok().body(true);
    }
}
