package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.teamproject_back.entity.User;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    List<User> findAllUsersByRole(int role);
    List<User> findAllUsersByRoleAndName(@Param("role") int role, @Param("name")String name);
    int deleteUserByUserId(List<Long> checkedIds);
    int updateUserByUserId(User user);
}
