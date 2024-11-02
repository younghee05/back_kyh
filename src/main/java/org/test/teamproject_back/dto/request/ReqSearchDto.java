package org.test.teamproject_back.dto.request;

import lombok.Data;

@Data
public class ReqSearchDto {
    private int page;
    private String title;
    private int limit;
    private String id;
}
