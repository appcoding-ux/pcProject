package com.project.pc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class WeekSalesDTO {

    private Date orderDay;

    private Long daySales;

    public WeekSalesDTO(Date orderDay, Long daySales){
        this.orderDay = orderDay;
        this.daySales = daySales;
    }
}
