package org.test.teamproject_back.repository;

import lombok.Value;
import org.apache.ibatis.annotations.Param;
import org.test.teamproject_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int save(User user);
    User findUserByUsername(String username);
    User findUserByUserId(Long userId);
    int findByName(String name);
    int updateUserInfo(User user);
    Integer findById(String username);
    int deleteByUserId(Long userId);
    int updateUserProfile(@Param("userId") Long userId,@Param("imgUrl") String imgUrl);
    String findUsername(@Param("name") String name, @Param("phoneNumber") String phoneNumber);
}
