package com.project.pc.repository;

import com.project.pc.domain.Orders;
import com.project.pc.dto.CategorySalesDTO;
import com.project.pc.dto.FoodListImageDTO;
import com.project.pc.dto.WeekSalesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {


    /*
     * 나중에 설명할 때는 이걸로 설명
     * 데이터를 계속 넣을 수는 없기 때문에 일정 기간 2024-05-15 ~ 2024-05-22일까지의 데이터로 구현
     * */
    @Query("SELECT new com.project.pc.dto.WeekSalesDTO(DATE(o.regDate), SUM(o.totalPrice)) " +
            "FROM Orders o " +
            "WHERE o.regDate >= :startDate  AND o.regDate < :endDate " +
            "GROUP BY DATE(o.regDate) " +
            "ORDER BY DATE(o.regDate) ")
    List<WeekSalesDTO> findWeekSales(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /*
    * 카테고리별 음식 판매 갯수
    * */
    @Query("select new com.project.pc.dto.CategorySalesDTO(f.category, count(f.category)) " +
            "from OrderFood od " +
            "left join Food f on f.foodNum = od.food.foodNum " +
            "group by f.category")
    List<CategorySalesDTO> categorySales();

    /*
    * 이걸로 일주일 기간 동안의 데이터로 구현할 수 있다
    * */
//    @Query("SELECT new com.project.pc.dto.WeekSalesDTO(DATE(o.regDate), SUM(o.totalPrice)) " +
//            "FROM Orders o " +
//            "WHERE o.regDate >= :startDate" +
//            "GROUP BY DATE(o.regDate) " +
//            "ORDER BY DATE(o.regDate) ")
//    List<WeekSalesDTO> findWeekSales(@Param("startDate") LocalDateTime startDate);
}
