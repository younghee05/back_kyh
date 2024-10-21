package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.response.RespUserInfoDto;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public RespUserInfoDto getUserInfo() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userMapper.findUserByUserId(principalUser.getId());
        System.out.println(user);

        return RespUserInfoDto.builder()
                .user(user)
                .build();
    }
}
