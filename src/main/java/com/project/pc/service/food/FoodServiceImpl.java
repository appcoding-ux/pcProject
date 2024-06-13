package com.project.pc.service.food;

import com.project.pc.domain.Food;
import com.project.pc.domain.Member;
import com.project.pc.domain.Orders;
import com.project.pc.dto.*;
import com.project.pc.repository.FoodRepository;
import com.project.pc.repository.MemberRepository;
import com.project.pc.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    @Value("${com.project.pc.upload.path}")
    private String uploadPath;

    private final FoodRepository foodRepository;

    @Override
    public void foodInsert(FoodDTO foodDTO, MultipartFile file) {
        Food food = dtoToEntity(foodDTO);

        String originFileName = file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        Path savePath = Paths.get(uploadPath, uuid + "_" + originFileName);

        try {
            file.transferTo(savePath);
        }catch (IOException e){
            e.printStackTrace();
        }

        food.addImage(uuid, originFileName);

        foodRepository.save(food);
    }

    @Override
    public FoodListImageDTO read(Long foodNum) {
        Food food = foodRepository.findById(foodNum).orElseThrow();

        return entityToDTO(food);
    }

    @Override
    public void updateFood(FoodDTO foodDTO, MultipartFile file) {
        Food food = foodRepository.findById(foodDTO.getFoodNum()).orElseThrow();

        String fileName = food.getImageSet().getUuid()+food.getImageSet().getFileName();

        Resource resource = new FileSystemResource(uploadPath+ File.separator+fileName);

        try {
            resource.getFile().delete();
        }catch (Exception e){
            e.printStackTrace();
        }

        food.clearImage();

        food.changeFood(foodDTO);

        String originFileName = file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        Path savePath = Paths.get(uploadPath, uuid + "_" + originFileName);

        try {
            file.transferTo(savePath);
        }catch (IOException e){
            e.printStackTrace();
        }

        food.addImage(uuid, originFileName);

        foodRepository.save(food);
    }

    @Override
    public void removeFood(Long foodNum) {
        Food food = foodRepository.findById(foodNum).orElseThrow();

        String fileName = food.getImageSet().getUuid()+"_"+food.getImageSet().getFileName();

        Resource resource = new FileSystemResource(uploadPath+ File.separator+fileName);

        try {
            resource.getFile().delete();
        }catch (Exception e){
            e.printStackTrace();
        }

        foodRepository.deleteById(foodNum);
    }

    @Override
    public List<FoodListImageDTO> foodAll() {
        List<Food> food = foodRepository.findAll();

        return food.stream().map(result -> entityToDTO(result)).collect(Collectors.toList());
    }

    @Override
    public List<FoodListImageDTO> categorySearch(String category) {
        List<Food> food = foodRepository.categorySearch(category);

        return food.stream().map(result -> entityToDTO(result)).collect(Collectors.toList());
    }

    @Override
    public List<FoodListImageDTO> keywordSearch(String keyword) {
        List<Food> food = foodRepository.keywordSearch(keyword);

        return food.stream().map(list -> entityToDTO(list)).collect(Collectors.toList());
    }

    @Override
    public List<WeekSalesDTO> weekSales() {
        return null;
    }

    @Override
    public PageResponseDTO<FoodListImageDTO> listWithAll(PageRequestDTO pageRequestDTO) {
        String category = pageRequestDTO.getCategory();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getFoodPageable();

        Page<Food> result = foodRepository.searchWithAll(category, keyword, pageable);

        List<FoodListImageDTO> list = result.getContent().stream().map(food -> entityToDTO(food)).collect(Collectors.toList());

        return PageResponseDTO.<FoodListImageDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(list)
                .total((int)result.getTotalElements())
                .build();
    }


}
