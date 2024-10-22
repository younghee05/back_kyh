package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.dto.response.RespUserInfoDto;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.exception.SignupException;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthService authService;

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

    public void modifyUserInfo(ReqModifyUserDto dto) throws SignupException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userMapper.findUserByUserId(principalUser.getId());

        if (!bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        if (!authService.checkPassword(dto.getPassword(), dto.getCheckPassword())) {
            throw new SignupException("비밀번호가 일치하지 않습니다.");
        }
        userMapper.updateUserInfo(dto.toUser(user.getUserId()));
    }
}
