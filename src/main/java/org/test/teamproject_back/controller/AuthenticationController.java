package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.test.teamproject_back.service.OAuth2Service;

@RestController
public class AuthenticationController {

    @Autowired
    private OAuth2Service oAuth2Service;



}
