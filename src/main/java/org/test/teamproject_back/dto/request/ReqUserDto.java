package org.test.teamproject_back.dto.request;

import lombok.Data;

@Data
public class ReqUserDto {
    private int page;
    private int limit;
    private int role;
}
