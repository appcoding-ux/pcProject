package com.project.pc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CategorySalesDTO {

    private String category;

    private Long count;

    public CategorySalesDTO(String category, Long count){
        this.category = category;
        this.count = count;
    }
}
