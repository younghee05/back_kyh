package org.test.teamproject_back.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.test.teamproject_back.entity.Role;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.entity.UserRoles;
import org.test.teamproject_back.repository.AdminMapper;
import org.test.teamproject_back.repository.UserMapper;

@Component
public class AdminDataInit implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(String... args) throws Exception {
        String username = "admin"; // 원하는 관리자 아이디
        String password = "admin123"; // 원하는 관리자 비밀번호
        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        User user = User.builder()
                        .username(username)
                        .name("관리자")
                        .email(password + "@example.com")
                        .password(encryptedPassword)
                        .phoneNumber("010-1234-5678")
                        .build();

        userMapper.save(user);

    }
}

