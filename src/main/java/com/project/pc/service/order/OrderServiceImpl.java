package com.project.pc.service.order;

import com.project.pc.domain.*;
import com.project.pc.dto.*;
import com.project.pc.repository.FoodRepository;
import com.project.pc.repository.MemberRepository;
import com.project.pc.repository.OrdersRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    private final FoodRepository foodRepository;

    private final OrdersRepository ordersRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void orderFood(OrderFoodDTO orderFoodDTO) {
        Member member = memberRepository.findById(orderFoodDTO.getId()).orElseThrow();

        Orders order = Orders.builder()
                .member(member)
                .totalPrice(orderFoodDTO.getTotalPrice())
                .orderRequest(orderFoodDTO.getOrderRequest())
                .build();

        for(String key : orderFoodDTO.getMemberSelectFood().keySet()){
            Long foodNum = Long.parseLong(key);
            int foodAmount = orderFoodDTO.getMemberSelectFood().get(key);

            Food food = foodRepository.findById(foodNum).orElseThrow();

            order.addOrderFood(food, foodAmount);
        }

        ordersRepository.save(order);
    }

    // 최근 일주일간 매출 
//    @Override
//    public List<WeekSalesDTO> weekSales(LocalDateTime startDate) {
//        return ordersRepository.findWeekSales(startDate, endDate);
//    }

    // 임시로 만들어 둔 2024-05-15 ~ 2024-05-22까지의 매출
    @Override
    public List<WeekSalesDTO> weekSales(LocalDateTime startDate, LocalDateTime endDate) {
        return ordersRepository.findWeekSales(startDate, endDate);
    }

    @Override
    public List<ManySalesFoodDTO> manySalesFood() {
        List<ManySalesFoodDTO> list = new ArrayList<>();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QOrderFood orderFood = QOrderFood.orderFood;
        QFood food = QFood.food;
        QFoodImage foodImage = QFoodImage.foodImage;

        Long maxCount = queryFactory
                .select(orderFood.count())
                .from(orderFood)
                .join(orderFood.food, food)
                .leftJoin(foodImage).on(food.foodNum.eq(foodImage.food.foodNum))
                .groupBy(food.foodNum)
                .orderBy(orderFood.count().desc())
                .limit(1)
                .fetchOne();

        List<Tuple> result = queryFactory
                .select(food, foodImage, orderFood.count())
                .from(orderFood)
                .join(orderFood.food, food)
                .leftJoin(foodImage).on(food.foodNum.eq(foodImage.food.foodNum))
                .groupBy(food.foodNum)
                .having(orderFood.count().eq(maxCount))
                .orderBy(orderFood.count().desc())
                .fetch();

        for(Tuple data : result){
            Food food1 = data.get(0, Food.class);

            food1.addImage(data.get(1, FoodImage.class).getUuid(), data.get(1, FoodImage.class).getFileName());

            Long count = data.get(2, Long.class);

            ManySalesFoodDTO dto = entityToDTO(food1, count);

            list.add(dto);
        }

        return list;
    }

    @Override
    public List<CategorySalesDTO> categorySales() {
        return ordersRepository.categorySales();
    }

}
