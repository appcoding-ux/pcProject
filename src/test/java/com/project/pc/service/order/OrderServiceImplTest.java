package com.project.pc.service.order;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void manySalesFood(){
        orderService.manySalesFood().forEach(list -> log.info(list));
    }
}