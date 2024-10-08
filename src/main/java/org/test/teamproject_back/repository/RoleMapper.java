package org.test.teamproject_back.repository;

import org.test.teamproject_back.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    int save(Role role);
    Role findByName(String name);
}
