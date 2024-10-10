package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqModifyUserDto;
import org.test.teamproject_back.dto.response.RespAdminDto;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.repository.AdminMapper;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

@Service
public class AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RespAdminDto getAllUsers(String roleName) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!principalUser.getAuthorities().contains(roleName)) {
            throw new RuntimeException();
        }

        User user = userMapper.findAllUsersByRole(roleName);
        return RespAdminDto.builder()
                .user(user)
                .build();
    }

    public void deleteById(Long userId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principalUser.getId() == userId)) {
            throw new RuntimeException();
        }
        adminMapper.deleteUser(userId);
        adminMapper.deleteUserRoles(userId);
    }

    public void modifyById(ReqModifyUserDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principalUser.getId() == dto.getUserId())) {
            throw new RuntimeException();
        }

        adminMapper.updateByUserId(dto.toEntity(bCryptPasswordEncoder, dto.getImg()));
    }

}
