package com.project.pc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderFoodDTO {

    private Map<String, Integer> memberSelectFood = new HashMap<>();

    private String orderRequest;

    private int totalPrice;

    private String id;
}