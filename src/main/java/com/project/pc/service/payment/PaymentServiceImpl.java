package com.project.pc.service.payment;

import com.project.pc.domain.Payment;
import com.project.pc.dto.PaymentDTO;
import com.project.pc.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PaymentDTO> payAll() {
        List<Payment> result = paymentRepository.findAll();

        List<PaymentDTO> list = new ArrayList<>();

        for(Payment payment : result){
            PaymentDTO paymentDTO = modelMapper.map(payment, PaymentDTO.class);

            list.add(paymentDTO);
        }

        return list;
    }

    @Override
    public PaymentDTO getPayment(Long num) {
        return modelMapper.map(paymentRepository.findById(num).orElseThrow(), PaymentDTO.class);
    }

}
