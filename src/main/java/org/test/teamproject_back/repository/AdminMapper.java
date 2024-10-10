package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.entity.UserRoles;

@Mapper
public interface AdminMapper {
    int deleteUser(Long userId);
    int deleteUserRoles(Long userId);
    int updateByUserId(User user);
}
