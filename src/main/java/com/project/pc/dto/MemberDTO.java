package com.project.pc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String id;

    private String password;

    private String email;

    private String name;

    private String phone;

    private boolean social;

    private boolean del;

    private LocalTime time;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
