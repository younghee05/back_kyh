package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.dto.response.RespUserDto;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.repository.AdminUserMapper;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.repository.UserRolesMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RespUserDto getAllUsers(int role) {
        List<User> user = null;

        if (role == 2) {
            user = adminUserMapper.findAllUsersByRole(role);
        } else if (role == 3) {
            user = adminUserMapper.findAllUsersByRole(role);
        }

        return RespUserDto.builder()
                .user(user)
                .build();
    }

    public RespUserDto searchUsers(int role, String name) {
        List<User> user = null;

        if (role == 2) {
            user = adminUserMapper.findAllUsersByRoleAndName(role, name.trim());
        } else if (role == 3) {
            user = adminUserMapper.findAllUsersByRoleAndName(role, name.trim());
        }

        return RespUserDto.builder()
                .user(user)
                .build();
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deleteUser(Long userId) {
        adminUserMapper.deleteUserByUserId(userId);
        userRolesMapper.deleteUserRolesByUserId(userId);
    }

    @Transactional(rollbackFor = SQLException.class)
    public void modifyUser(ReqModifyUserDto dto) {
        adminUserMapper.updateUserByUserId(dto.toUser(bCryptPasswordEncoder));
    }

}
