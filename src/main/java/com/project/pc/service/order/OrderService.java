package com.project.pc.service.order;

import com.project.pc.domain.Food;
import com.project.pc.dto.*;
import com.querydsl.core.Tuple;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    void orderFood(OrderFoodDTO orderFoodDTO);

    // 최근 일주일간 매출
    List<WeekSalesDTO> weekSales(LocalDateTime startDate, LocalDateTime endDate);

    // 가장 많이 팔린 메뉴
    List<ManySalesFoodDTO> manySalesFood();

    // 카테고리별 판매 갯수
    List<CategorySalesDTO> categorySales();

    default ManySalesFoodDTO entityToDTO(Food food, Long count){
        return ManySalesFoodDTO.builder()
                .foodNum(food.getFoodNum())
                .foodContent(food.getFoodContent())
                .foodName(food.getFoodName())
                .category(food.getCategory())
                .price(food.getPrice())
                .regDate(food.getRegDate())
                .modDate(food.getModDate())
                .fileName(food.getImageSet().getUuid() + "_" + food.getImageSet().getFileName())
                .count(count)
                .build();
    }
}
