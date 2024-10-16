package org.test.teamproject_back.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReqSendMailDto {
    private String toEmail;
    private String subject;
    private String content;
}
