package com.project.pc.domain;

import com.project.pc.dto.FoodDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodNum;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String foodContent;

    @Column(nullable = false)
    private String category;

    @OneToOne(mappedBy = "food", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @Builder.Default
    private FoodImage imageSet = new FoodImage();

    public void addImage(String uuid, String fileName){
        imageSet = FoodImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .food(this)
                .build();
    }

    public void clearImage(){
        this.imageSet = null;
    }

    public void changeFood(FoodDTO foodDTO){
        this.foodName = foodDTO.getFoodName();
        this.foodContent = foodDTO.getFoodContent();
        this.price = foodDTO.getPrice();
        this.category = foodDTO.getCategory();
    }
}
