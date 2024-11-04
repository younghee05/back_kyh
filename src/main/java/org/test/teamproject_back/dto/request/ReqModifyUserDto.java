package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.test.teamproject_back.entity.Address;
import org.test.teamproject_back.entity.User;

@Builder
@Data
public class ReqModifyUserDto {
    private String password;
    private String changePassword;
    private String checkPassword;
    private String email;
    private String address;
    private String detailAddress;
    private int zipCode;

    public User toUpdateUser(Long userId, BCryptPasswordEncoder bycryptPasswordEncoder) {
        return User.builder()
                .userId(userId)
                .password(bycryptPasswordEncoder.encode(changePassword))
                .email(email)
                .build();
    }

    public User toUser(Long userId, String password) {
        return User.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .build();
    }

    public Address toAddress(Long userId) {
        return Address.builder()
                .userId((userId))
                .address(address)
                .detailAddress(detailAddress)
                .zipCode(zipCode)
                .build();
    }
}
