package com.project.pc.repository.search;

import com.project.pc.domain.Food;
import com.project.pc.domain.QFood;
import com.project.pc.dto.FoodListImageDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl(){
        super(Food.class);
    }

    @Override
    public Page<Food> searchWithAll(String category, String keyword, Pageable pageable) {

        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(category != null){
            booleanBuilder.or(food.category.contains(category));

            query.where(booleanBuilder);
        }else if(keyword != null){
            booleanBuilder.or(food.foodName.contains(keyword));

            query.where(booleanBuilder);
        }

        query.where(food.foodNum.gt(0L));

        getQuerydsl().applyPagination(pageable, query);

        List<Food> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public List<Food> categorySearch(String category) {
        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(category.equals("전체")){
            return query.fetch();
        }else {
            booleanBuilder.or(food.category.contains(category));

            query.where(booleanBuilder);
        }

        query.where(food.foodNum.gt(0L));

        return query.fetch();
    }

    @Override
    public List<Food> keywordSearch(String keyword) {
        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(keyword.equals("전체")){
            return query.fetch();
        }else {
            booleanBuilder.or(food.foodName.contains(keyword));

            query.where(booleanBuilder);
        }

        query.where(food.foodNum.gt(0L));

        return query.fetch();
    }
}
