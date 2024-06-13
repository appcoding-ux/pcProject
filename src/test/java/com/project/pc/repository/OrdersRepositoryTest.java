package com.project.pc.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void weekSalesTest(){
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 15, 0,0,0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 22, 0, 0, 0);

        ordersRepository.findWeekSales(startDate, endDate).forEach(list -> log.info(list));
    }
}