package com.project.pc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoMessageDTO {
    private String objType;

    private String text;

    private String webUrl;

    private String mobileUrl;

    private String btnTitle;
}
