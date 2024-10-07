package org.test.teamproject_back.service;

import org.test.teamproject_back.dto.request.ReqSignupDto;
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

    @Transactional(rollbackFor = SignupException.class)
    public void signup(ReqSignupDto dto) throws SignupException {

        User user = null;
        try {
            user = userMapper.findByUsername(dto.getUsername());
            if (user == null) {
                user = dto.toEntity(bCryptPasswordEncoder);
                userMapper.save(user);
            }

            Role role = roleMapper.findByName("ROLE_USER");
            if (role == null) {
                role = Role.builder()
                        .name("ROLE_USER")
                        .build();

            }
            roleMapper.save(role);

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getId())
                    .roleId(role.getId())
                    .build();
            userRolesMapper.save(userRoles);

        } catch (Exception e) {
            throw new SignupException(e.getMessage());
        }
    }

    // username 중복 확인

    // password, checkPassword 같은지 확인

}
