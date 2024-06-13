package com.project.pc.repository;

import com.project.pc.domain.Food;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Log4j2
class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void foodInsert(){
        for(int i = 2; i <= 1000; i++){
            Food food = Food.builder()
                    .foodName("음식이름"+i)
                    .foodContent("음식설명입니다." + i)
                    .price(5000+i)
                    .category("음식")
                    .build();

            String uuid = UUID.randomUUID().toString();
            String fileName = "aaa"+ i + ".jpg";

            food.addImage(uuid, fileName);

            foodRepository.save(food);
        }
    }
}