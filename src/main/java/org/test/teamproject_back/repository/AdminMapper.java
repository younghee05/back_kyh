package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.entity.UserRoles;

@Mapper
public interface AdminMapper {
    int deleteUserByUserId(Long userId);
    int deleteUserRolesByUserId(Long userId);
    int updateByUserId(User user);
}
