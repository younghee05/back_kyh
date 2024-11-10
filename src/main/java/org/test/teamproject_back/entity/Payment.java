package org.test.teamproject_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    private Long paymentId;
    private Long orderId;
    private String paymentMethod;
    private String paymentStatus;
    private Long amount;
    private String paymentNum;
    private String paymentDate;
}
