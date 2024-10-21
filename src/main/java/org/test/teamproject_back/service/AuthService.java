package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.test.teamproject_back.dto.request.ReqSigninDto;
import org.test.teamproject_back.dto.request.ReqSignupDto;
import org.test.teamproject_back.dto.response.RespSigninDto;
import org.test.teamproject_back.dto.response.RespSignupDto;
import org.test.teamproject_back.entity.Role;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.entity.UserRoles;
import org.test.teamproject_back.exception.SignupException;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.repository.RoleMapper;
import org.test.teamproject_back.repository.UserRolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.security.jwt.JwtProvider;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private JwtProvider jwtProvider;

    @Value("${user.profile.img.default}")
    private String defaultProfileImg;

    @Transactional(rollbackFor = SignupException.class)
    public RespSignupDto signup(ReqSignupDto dto, String roleName) throws SignupException {

        User user = null;
        try {
            if (isDuplicateUsername(dto.getUsername())) {
                throw new SignupException("이미 존재하는 사용자 입니다.");
            }
            if (!checkPassword(dto.getPassword(), dto.getCheckPassword())) {
                throw new SignupException("비밀번호가 일치하지 않습니다.");
            }

            user = dto.toEntity(bCryptPasswordEncoder, defaultProfileImg);
            userMapper.save(user);

            Role role = roleMapper.findByName(roleName);
            if (role == null) {
                role = Role.builder()
                        .name(roleName)
                        .build();

                roleMapper.save(role);
            }

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getUserId())
                    .roleId(role.getRoleId())
                    .build();
            userRolesMapper.save(userRoles);
            user.setUserRoles(Set.of(userRoles));
        } catch (Exception e) {
            throw new SignupException(e.getMessage());
        }

        return RespSignupDto.builder()
                .user(user)
                .build();
    }

    // username 중복 확인
    public boolean isDuplicateUsername(String username) {
        return Optional.ofNullable(userMapper.findUserByUsername(username)).isPresent();
    }

    // password, checkPassword 같은지 확인
    public boolean checkPassword(String password, String checkPassword) {
        return checkPassword.equals(password);
    }

    public RespSigninDto signin(ReqSigninDto dto) {
       User user = checkUsernameAndPassword(dto.getUsername(), dto.getPassword());
        return RespSigninDto.builder()
                .expireDate(jwtProvider.getExpireDate().toLocaleString())
                .accessToken(jwtProvider.generateAccessToken(user))
                .build();
    }

    public User checkUsernameAndPassword(String username, String password) {
        User user = userMapper.findUserByUsername(username);
//        System.out.println(user);
        if (user == null) {;
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }
        return user;
    }

}
