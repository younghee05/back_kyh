package org.test.teamproject_back.init;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.test.teamproject_back.entity.Role;
import org.test.teamproject_back.repository.RoleMapper;

@Component
public class RoleDataInit implements CommandLineRunner {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void run(String... args) throws Exception {

        if (roleMapper.findByName(("ROLE_ADMIN")) == null) {
            Role role = Role.builder()
                    .name("ROLE_ADMIN")
                    .build();
            roleMapper.save(role);
        }

        if (roleMapper.findByName(("ROLE_USER")) == null) {
            Role role = Role.builder()
                    .name("ROLE_USER")
                    .build();
            roleMapper.save(role);
        }
    }
}
