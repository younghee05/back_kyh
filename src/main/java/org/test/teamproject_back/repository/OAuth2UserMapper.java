package org.test.teamproject_back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.test.teamproject_back.entity.OAuth2User;

@Mapper
public interface OAuth2UserMapper {
    int save(OAuth2User oAuth2User);
}
