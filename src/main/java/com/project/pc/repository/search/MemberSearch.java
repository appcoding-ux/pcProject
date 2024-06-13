package com.project.pc.repository.search;

import com.project.pc.domain.Member;
import com.project.pc.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberSearch {
    Page<Member> searchWithAll(String keyword, Pageable pageable);
}
