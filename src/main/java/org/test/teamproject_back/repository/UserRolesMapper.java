package org.test.teamproject_back.repository;

import org.test.teamproject_back.entity.Role;
import org.test.teamproject_back.entity.UserRoles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRolesMapper {
    int save(UserRoles userRoles);
    UserRoles findUserRolesByUserId(Long userId);
    int deleteUserRolesByUserId(List<Long> checkedIds);
    int updateUserRoles(Long userId, int roleId);
    int deleteUserRole(Long userId);
}
