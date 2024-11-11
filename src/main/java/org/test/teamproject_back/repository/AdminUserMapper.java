package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.teamproject_back.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminUserMapper {
    List<User> findAllUsersByRole(Map<String, Object> paging);
    List<User> findAllUsersByRoleAndName(Map<String, Object> paging);
    int deleteUserByUserId(List<Long> checkedIds);
    int updateUserByUserId(User user);
}
