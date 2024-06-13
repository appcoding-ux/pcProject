package com.project.pc.service.payment;

import com.project.pc.dto.PaymentDTO;

import java.time.LocalTime;
import java.util.List;

public interface PaymentService {

    List<PaymentDTO> payAll();

    PaymentDTO getPayment(Long num);
}
