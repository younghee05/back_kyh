package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.util.Date;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long userId;
    private String username;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String img;

    private Set<UserRoles> userRoles;

    public PrincipalUser toPrincipal() {
        return PrincipalUser.builder()
                .id(userId)
                .username(username)
                .password(password)
                .roles(userRoles)
                .build();
    }
}
