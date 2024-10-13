package org.test.teamproject_back.repository;

import org.test.teamproject_back.entity.UserRoles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRolesMapper {
    int save(UserRoles userRoles);
    UserRoles findUserRolesByUserId(Long UserId);
    int deleteUserRolesByUserId(Long userId);
    int updateUserRoles(Long userId, int roleId);
}
