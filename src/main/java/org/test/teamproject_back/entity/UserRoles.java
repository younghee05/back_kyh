package org.test.teamproject_back.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRoles {
    private Long id;
    private Long userId;
    private Long roleId;
}
