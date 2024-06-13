package com.project.pc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String keyword;

    private String category;

    private String link;

    public Pageable getPageable(){
        return PageRequest.of(this.page-1, this.size);
    }

    public Pageable getFoodPageable(){
        return PageRequest.of(this.page-1, this.size, Sort.by("foodNum").descending());
    }

    public void foodPageSize(){
        this.size = 15;
    }

    public String getLink(){
        if(link == null){
            StringBuilder builder = new StringBuilder();

            builder.append("page=" + this.page);

            builder.append("&size=" + this.size);

            if(keyword != null){
                try {
                    builder.append("&keyword" + URLEncoder.encode(keyword, "UTF-8"));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }else if(category != null){
                try {
                    builder.append("&category" + URLEncoder.encode(category, "UTF-8"));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
            link = builder.toString();
        }
        return link;
    }
}
