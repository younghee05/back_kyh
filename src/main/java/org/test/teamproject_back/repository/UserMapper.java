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
    int isProductOwned(Long userId, Long productId);
}
