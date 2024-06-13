package com.project.pc.repository;

import com.project.pc.domain.Food;
import com.project.pc.repository.search.FoodSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<Food, Long>, FoodSearch {

    @EntityGraph(attributePaths = "imageSet")
    @Query("select f From Food f where f.foodNum = :foodNum")
    Food findByFoodWithImage(Long foodNum);
}