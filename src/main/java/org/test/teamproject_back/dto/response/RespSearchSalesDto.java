package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Payment;

import java.util.List;

@Builder
@Data
public class RespSearchSalesDto {
    private List<Payment> paymentList;
    private int count;
}
