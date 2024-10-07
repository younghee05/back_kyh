package org.test.teamproject_back.repository;

import org.test.teamproject_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
}
