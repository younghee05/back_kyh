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
import org.test.teamproject_back.exception.InvalidInputException;
import org.test.teamproject_back.repository.AdminUserMapper;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.repository.UserRolesMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RespAdminDto getAllUsers(int role) {
        List<User> user = null;

        if (role == 2) {
            user = adminUserMapper.findAllUsersByRole(role);
        } else if (role == 3) {
            user = adminUserMapper.findAllUsersByRole(role);
        }

        return RespAdminDto.builder()
                .user(user)
                .build();
    }

    public RespAdminDto searchUsers(int role, String name) {
        List<User> user = null;

        if (role == 2) {
            user = adminUserMapper.findAllUsersByRoleAndName(role, name.trim());
        } else if (role == 3) {
            user = adminUserMapper.findAllUsersByRoleAndName(role, name.trim());
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

        adminUserMapper.deleteUserByUserId(userId);
        userRolesMapper.deleteUserRolesByUserId(userId);
    }

    @Transactional(rollbackFor = SQLException.class)
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

        adminUserMapper.updateUserByUserId(dto.toEntity(bCryptPasswordEncoder));
    }

}
