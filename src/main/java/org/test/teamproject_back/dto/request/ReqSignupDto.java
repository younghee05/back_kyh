package org.test.teamproject_back.dto.request;

import org.test.teamproject_back.entity.Address;
import org.test.teamproject_back.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class ReqSignupDto {
    @Pattern(regexp = "^[a-z0-9]{6,}$", message = "사용자이름은 6자이상의 영소문자, 숫자 조합이어야합니다.")
    private String username;
    @NotBlank(message = "성명은 공백일 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "이름은 한글 또는 영문자만 포함할 수 있습니다.")
    private String name;
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "비밀번호는 8자이상 16자이하의 영대소문, 숫자, 특수문자(~!@#$%^&*?)를 포함하여합니다.")
    private String password;
    private String checkPassword;
    @Pattern(regexp = "^01[0-9]{1,2}-[0-9]{3,4}-[0-9]{4}$", message = "전화번호는 010-1234-5678 형식이어야합니다.")
    private String phoneNumber;

    public User toEntity(BCryptPasswordEncoder bycryptPasswordEncoder, String img) {
        return User.builder()
                .username(username)
                .name(name)
                .email(email)
                .password(bycryptPasswordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .img(img)
                .build();
    }
}
