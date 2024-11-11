package org.test.teamproject_back.dto.request;

import lombok.Data;

@Data
public class ReqSearchUserDto {
    private int page;
    private String name;
    private int limit;
    private int role;

}
