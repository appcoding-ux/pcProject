package com.project.pc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManySalesFoodDTO {

    private Long foodNum;

    private String foodName;

    private int price;

    private String foodContent;

    private String category;

    private String fileName;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private Long count;
}
