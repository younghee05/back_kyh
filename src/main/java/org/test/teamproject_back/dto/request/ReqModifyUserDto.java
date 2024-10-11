package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.test.teamproject_back.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReqModifyUserDto {

    private Long userId;
    @Pattern(regexp = "^[a-z0-9]{6,}$", message = "사용자이름은 6자이상의 영소문자, 숫자 조합이어야합니다.")
    private String username;
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "이름은 한글 또는 영문자만 포함할 수 있습니다.")
    private String name;
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "비밀번호는 8자이상 16자이하의 영대소문, 숫자, 특수문자(~!@#$%^&*?)를 포함하여합니다.")
    private String password;
    @Pattern(regexp = "^01[0-9]{1,2}-[0-9]{3,4}-[0-9]{4}$", message = "전화번호는 010-1234-5678 형식이어야합니다.")
    private String phoneNumber;
    private String img;

    public User toEntity(BCryptPasswordEncoder bycryptPasswordEncoder) {
        return User.builder()
                .id(userId)
                .username(username)
                .name(name)
                .email(email)
                .password(bycryptPasswordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .img(img)
                .build();
    }
}
