package com.project.pc.service.food;

import com.project.pc.domain.Food;
import com.project.pc.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FoodService {

    void foodInsert(FoodDTO foodDTO, MultipartFile file);

    PageResponseDTO<FoodListImageDTO> listWithAll(PageRequestDTO pageRequestDTO);

    FoodListImageDTO read(Long foodNum);

    void updateFood(FoodDTO foodDTO, MultipartFile file);

    void removeFood(Long foodNum);

    List<FoodListImageDTO> foodAll();

    List<FoodListImageDTO> categorySearch(String category);

    List<FoodListImageDTO> keywordSearch(String keyword);

    List<WeekSalesDTO> weekSales();

    default Food dtoToEntity(FoodDTO foodDTO){

        return Food.builder()
                .foodNum(foodDTO.getFoodNum())
                .foodContent(foodDTO.getFoodContent())
                .foodName(foodDTO.getFoodName())
                .price(foodDTO.getPrice())
                .category(foodDTO.getCategory())
                .build();
    }

    default FoodListImageDTO entityToDTO(Food food){
        return FoodListImageDTO.builder()
                .foodNum(food.getFoodNum())
                .foodContent(food.getFoodContent())
                .foodName(food.getFoodName())
                .category(food.getCategory())
                .price(food.getPrice())
                .regDate(food.getRegDate())
                .modDate(food.getModDate())
                .fileName(food.getImageSet().getUuid() + "_" + food.getImageSet().getFileName())
                .build();
    }
}
