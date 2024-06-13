package com.project.pc.controller;

import com.project.pc.service.food.FoodService;
import com.project.pc.service.payment.PaymentService;
import com.project.pc.service.seat.SeatService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MainController {

    private final PaymentService paymentService;

    private final SeatService seatService;

    private final FoodService foodService;


    // 로그인 후 좌석 선택 페이지 이동
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("paymentDTO", paymentService.payAll());
        model.addAttribute("seatDTO", seatService.seatAll());
        model.addAttribute("foodDTO", foodService.foodAll());

        return "index";
    }

    // 좌석 선택 후 PC 사용 페이지로 이동
    @PostMapping("/pcLogin")
    public String pcLogin(Long seat, Model model, String id, RedirectAttributes redirectAttributes){
        log.info("로그인한 유저는 몇 번에 앉나요? " + seat);

        boolean frag = seatService.updateSeat(seat, id);

        if(!frag){
            redirectAttributes.addFlashAttribute("error", "runningOutTime");

            return "redirect:/";
        }

        model.addAttribute("seatNum", seat);
        model.addAttribute("paymentDTO", paymentService.payAll());
        model.addAttribute("foodDTO", foodService.foodAll());

        return "pcLogin";
    }

    // PC를 사용중일 때 로그아웃 했을 때
    @PostMapping("/pcLogout")
    public String pcLogout(Long seat, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        seatService.logoutSeat(seat);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        redirectAttributes.addFlashAttribute("logout", "logout");

        return "redirect:/member/login";
    }

    // PC를 사용중이지 않고 좌석 선택화면에서 로그아웃 했을 때
    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        redirectAttributes.addFlashAttribute("logout", "logout");

        return "redirect:/member/login";
    }
}
