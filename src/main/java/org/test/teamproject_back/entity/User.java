package org.test.teamproject_back.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Builder
@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Date createdAt;
}
