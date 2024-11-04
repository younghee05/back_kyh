package org.test.teamproject_back.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqDeleteReviewDto {
    private List<Long> checkedIds;
}
