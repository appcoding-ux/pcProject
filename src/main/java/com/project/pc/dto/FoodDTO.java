package com.project.pc.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodDTO {

    private Long foodNum;

    private String foodName;

    private int price;

    private String foodContent;

    private String category;
}