package org.test.teamproject_back.repository;

import lombok.Value;
import org.apache.ibatis.annotations.Param;
import org.test.teamproject_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
    User findById(Long userId);
    int findByName(String name);
}
