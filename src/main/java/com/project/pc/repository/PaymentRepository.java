package com.project.pc.repository;

import com.project.pc.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p.paymentTime from Payment p where p.num = :num")
    LocalTime selectTime(Long num);
}
