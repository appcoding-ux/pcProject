package com.project.pc.service.member;

import com.project.pc.dto.MemberDTO;
import com.project.pc.dto.PageRequestDTO;
import com.project.pc.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

public interface MemberService {

    void memberInsert(MemberDTO memberDTO);

    MemberDTO idCheck(String id);

    MemberDTO findMember(String id);

    boolean emailCheck(String email);

    void addTime(Long paymentHour, String id);

    void updateTime(String id);

    void removeMember(String id);

    PageResponseDTO<MemberDTO> listWithAll(PageRequestDTO pageRequestDTO);

    void updatePass(String email, String password);
}
