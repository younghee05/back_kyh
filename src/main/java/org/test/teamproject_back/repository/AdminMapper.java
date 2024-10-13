package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.entity.UserRoles;

@Mapper
public interface AdminMapper {
    User findUserById(Long userId);
    int deleteUserByUserId(Long userId);
    int updateUserByUserId(User user);
}
