package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.test.teamproject_back.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class ReqModifyAdminUserDto {

    private Long checkId;
    @Pattern(regexp = "^[a-z0-9]{6,}$", message = "사용자이름은 6자이상의 영소문자, 숫자 조합이어야합니다.")
    private String username;
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "이름은 한글 또는 영문자만 포함할 수 있습니다.")
    private String name;
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @Pattern(regexp = "^01[0-9]{1,2}-[0-9]{3,4}-[0-9]{4}$", message = "전화번호는 010-1234-5678 형식이어야합니다.")
    private String phoneNumber;

    public User toUser() {
        return User.builder()
                .userId(checkId)
                .username(username)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
