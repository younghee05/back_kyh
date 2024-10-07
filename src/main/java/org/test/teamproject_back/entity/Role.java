package org.test.teamproject_back.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Role {
    private Long id;
    private String name;
}
