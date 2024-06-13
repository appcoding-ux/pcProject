package com.project.pc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pc.dto.PageRequestDTO;
import com.project.pc.dto.WeekSalesDTO;
import com.project.pc.service.food.FoodService;
import com.project.pc.service.member.MemberService;
import com.project.pc.service.order.OrderService;
import com.project.pc.service.seat.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final SeatService seatService;

    private final MemberService memberService;

    private final FoodService foodService;

    private final OrderService orderService;

    // 현재 로그인해서 사용중인 좌석 현황
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/seat")
    public String adminSeat(Model model, PageRequestDTO pageRequestDTO){
        model.addAttribute("seatDTO", seatService.seatAll());

        return "admin/adminSeat";
    }

    // 피시방 음식 관리
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/food")
    public String adminFood(Model model, PageRequestDTO pageRequestDTO){
        System.out.println("음식 이름 검색어 : " + pageRequestDTO.getKeyword());
        System.out.println("카테고리 : " + pageRequestDTO.getCategory());

        pageRequestDTO.foodPageSize();

        model.addAttribute("responseDTO", foodService.listWithAll(pageRequestDTO));
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "admin/adminFood";
    }

    // 현재 음식에 대한 판매 현황
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/sale")
    public String adminSale(Model model, PageRequestDTO pageRequestDTO) throws JsonProcessingException {

        // 임의 날자 데이터
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 15, 0,0,0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 22, 23, 59, 59);

        model.addAttribute("weekSalesDTO", orderService.weekSales(startDate, endDate));
        model.addAttribute("manySalesFoodDTO", orderService.manySalesFood());
        model.addAttribute("categorySalesDTO", orderService.categorySales());

        System.out.println(orderService.categorySales());

        return "admin/adminSale";
    }

    // 회원 관리 페이지
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/member")
    public String adminMember(Model model, PageRequestDTO pageRequestDTO){
        model.addAttribute("responseDTO", memberService.listWithAll(pageRequestDTO));
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        return "admin/adminMember";
    }
}
