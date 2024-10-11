package org.test.teamproject_back.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.entity.Role;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.entity.UserRoles;
import org.test.teamproject_back.exception.SignupException;
import org.test.teamproject_back.repository.AdminMapper;
import org.test.teamproject_back.repository.RoleMapper;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.repository.UserRolesMapper;

@Component
public class AdminDataInit implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Value("${user.profile.img.default}")
    private String img;

    @Override
    @Transactional(rollbackFor = SignupException.class)
    public void run(String... args) throws Exception {
        String username = "admin12"; // 원하는 관리자 아이디
        String password = "Admin1q2w3e4r!!"; // 원하는 관리자 비밀번호
        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        if (userMapper.findByName("관리자") == 0) {
            User user = User.builder()
                    .username(username)
                    .name("관리자")
                    .email(password + "@example.com")
                    .password(encryptedPassword)
                    .phoneNumber("010-1234-5678")
                    .img(img)
                    .build();

            userMapper.save(user);

            Role role = roleMapper.findByName("ROLE_ADMIN");
            if (role == null) {
                role = Role.builder()
                        .name("ROLE_ADMIN")
                        .build();
                roleMapper.save(role);
            }

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getId())
                    .roleId(role.getId())
                    .build();

            userRolesMapper.save(userRoles);
        }
    }
}

