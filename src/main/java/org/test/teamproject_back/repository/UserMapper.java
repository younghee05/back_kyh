package org.test.teamproject_back.repository;

import org.test.teamproject_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
    User findById(Long userId);
    int findByName(String name);
    User findAllUsersByRole(String roleName);
}
