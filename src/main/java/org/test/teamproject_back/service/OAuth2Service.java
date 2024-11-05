package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.entity.UserRoles;
import org.test.teamproject_back.repository.RoleMapper;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.repository.UserRolesMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class OAuth2Service implements OAuth2UserService {

    @Autowired
    private DefaultOAuth2UserService defaultOAuth2UserService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public org.springframework.security.oauth2.core.user.OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        User user = null;

        System.out.println(" 여기>>>>>>>>>>>>" + attributes);



        Map<String, Object> oAuth2Attributes = new HashMap<>();
        oAuth2Attributes.put("provider", userRequest.getClientRegistration().getClientName());

        switch (userRequest.getClientRegistration().getClientName()) {
            case "Google":
                if(userMapper.findById((String) attributes.get("sub")) == null) {
                    user = User.builder()
                            .username((String) attributes.get("sub"))
                            .name((String) attributes.get("name"))
                            .email((String) attributes.get("email"))
                            .password(passwordEncoder.encode("1Q2w3e4r!!"))
                            .img((String) attributes.get("picture"))
                            .build();
                    userMapper.save(user);
                    UserRoles userRoles = UserRoles.builder()
                            .userId(user.getUserId())
                            .roleId(3)
                            .build();
                    userRolesMapper.save(userRoles);
                }
                oAuth2Attributes.put("id", attributes.get("sub").toString());
                break;
            case "Naver":
                Map<String, Object> response = (Map<String, Object>) attributes.get("response");
                if(userMapper.findById((String) response.get("id")) == null) {
                    user = User.builder()
                            .username((String)response.get("id"))
                            .name((String)response.get("name"))
                            .email((String)response.get("email"))
                            .password(passwordEncoder.encode("1Q2w3e4r!!"))
                            .phoneNumber((String)response.get("mobile"))
                            .img((String)response.get("profile_image"))
                            .build();
                    userMapper.save(user);
                    UserRoles userRoles = UserRoles.builder()
                            .userId(user.getUserId())
                            .roleId(3)
                            .build();
                    userRolesMapper.save(userRoles);
                }
                attributes = (Map<String, Object>) attributes.get("response");
                oAuth2Attributes.put("id", attributes.get("id").toString());
                break;
        }

        return new DefaultOAuth2User(new HashSet<>(), oAuth2Attributes, "id");
    }

}
