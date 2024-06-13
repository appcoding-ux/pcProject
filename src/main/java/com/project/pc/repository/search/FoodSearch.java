package com.project.pc.repository.search;

import com.project.pc.domain.Food;
import com.project.pc.dto.FoodListImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodSearch {

    Page<Food> searchWithAll(String category , String keyword, Pageable pageable);

    List<Food> categorySearch(String category);

    List<Food> keywordSearch(String keyword);
}
