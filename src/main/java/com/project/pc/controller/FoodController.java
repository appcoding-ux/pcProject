package com.project.pc.controller;

import com.project.pc.dto.FoodDTO;
import com.project.pc.dto.FoodListImageDTO;
import com.project.pc.dto.OrderFoodDTO;
import com.project.pc.service.food.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/food")
@Log4j2
public class FoodController {

    private final FoodService foodService;

    @Value("${com.project.pc.upload.path}")
    private String uploadPath;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/insertPage")
    public String foodInsertGet(){
        return "admin/adminFoodInsert";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/read/{foodNum}")
    public String foodRead(@PathVariable("foodNum") Long foodNum, Model model){
        model.addAttribute("foodInfo", foodService.read(foodNum));

        return "admin/adminFoodRead";
    }

    @PostMapping("/insert")
    public String foodInsertPost(FoodDTO foodDTO, RedirectAttributes redirectAttributes, @RequestParam("foodImage") MultipartFile file){
        String fileName = file.getOriginalFilename();

        if(file.getSize() > 10 * 1024 * 1024){
            redirectAttributes.addFlashAttribute("result", "fileSizeOver");
        }else if(fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("png")){
            redirectAttributes.addFlashAttribute("result", "noImg");
        }

        foodService.foodInsert(foodDTO, file);

        redirectAttributes.addFlashAttribute("result", "result");

        return "redirect:/food/insertPage";
    }

    @PostMapping("/update")
    public String foodUpdate(FoodDTO foodDTO, RedirectAttributes redirectAttributes, @RequestParam("foodImage") MultipartFile file){
        System.out.println(foodDTO);

        String fileName = file.getOriginalFilename();

        if(file.getSize() > 10 * 1024 * 1024){
            redirectAttributes.addFlashAttribute("result", "fileSizeOver");
        }else if(fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("png")){
            redirectAttributes.addFlashAttribute("result", "noImg");
        }

        foodService.updateFood(foodDTO, file);

        redirectAttributes.addFlashAttribute("result", "foodUpdate");

        return "redirect:/food/read/"+foodDTO.getFoodNum();
    }

    @PostMapping("/remove")
    public String foodRemove(Long foodNum, RedirectAttributes redirectAttributes){
        foodService.removeFood(foodNum);

        redirectAttributes.addFlashAttribute("result", "foodRemove");

        return "redirect:/admin/food";
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fileName") String fileName){
        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<FoodListImageDTO>> orderCategorySearch(@PathVariable("category") String category) {
        return new ResponseEntity<>(foodService.categorySearch(category), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<FoodListImageDTO>> orderKeywordSearch(@PathVariable("keyword") String keyword){
        System.out.println(keyword);
        return new ResponseEntity<>(foodService.keywordSearch(keyword), HttpStatus.OK);
    }
}
