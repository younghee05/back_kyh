package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.dto.response.RespAdminDto;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.repository.AdminMapper;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RespAdminDto getAllUsers(String roleName) {
        User user = null;

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<String> authorities = principalUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!authorities.contains("ROLE_ADMIN")) {
            throw new AuthenticationServiceException("조회 할 수 있는 권한이 없습니다.");
        }

        if ("user".equals(roleName.trim())) {
            System.out.println("ddd");
            user = userMapper.findAllUsersByRole("ROLE_USER");
        } else if ("manager".equals(roleName.trim())) {
            user = userMapper.findAllUsersByRole("ROLE_MANAGER");
        }

        return RespAdminDto.builder()
                .user(user)
                .build();
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deleteUser(Long userId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<String> authorities = principalUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!authorities.contains("ROLE_ADMIN")) {
            throw new AuthenticationServiceException("삭제 할 수 있는 권한이 없습니다.");
        }

        adminMapper.deleteUserByUserId(userId);
        adminMapper.deleteUserRolesByUserId(userId);
    }

    public void modifyUser(ReqModifyUserDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<String> authorities = principalUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!authorities.contains("ROLE_ADMIN")) {
            throw new AuthenticationServiceException("수정 할 수 있는 권한이 없습니다.");
        }

        adminMapper.updateByUserId(dto.toEntity(bCryptPasswordEncoder));
    }

}
