package com.project.pc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long num;

    private LocalTime paymentTime;

    private int price;

    private String paymentName;

    private Long paymentHour;
}
