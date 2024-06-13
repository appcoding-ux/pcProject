package com.project.pc.repository;

import com.project.pc.domain.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@Log4j2
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void insertPayment(){
        Payment payment = Payment.builder()
                .paymentTime(LocalTime.of(18, 0))
                .price(15000)
                .paymentHour(18L)
                .build();

        paymentRepository.save(payment);
    }

    @Test
    public void payAll(){
        List<Payment> list = paymentRepository.findAll();

        list.forEach(result -> log.info(result));
    }
}