package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.User;

import java.util.List;

@Builder
@Data
public class RespAdminDto {
    private List<User> user;
}
