package com.project.pc.repository;

import com.project.pc.domain.Member;
import com.project.pc.domain.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.InputStream;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertTest() {
            Member member = Member.builder()
                .id("test")
                .password(passwordEncoder.encode("test"))
                .email("test@test.com")
                .phone("0101010101")
                .name("테스트계정")
                .build();

            member.addRole(MemberRole.USER);

            memberRepository.save(member);
    }
    @Test
    public void testSearchAll(){
        String keyword = "dodo";

        Pageable pageable = PageRequest.of(0, 10);

        Page<Member> result = memberRepository.searchWithAll(keyword, pageable);

        log.info(result.getTotalPages());

        log.info(result.getSize());

        result.getContent().forEach(member -> log.info(member));
    }

    @Test
    public void readMemberTest(){
        Member member = memberRepository.findById("iy398884@gmail.com").orElseThrow();

        log.info(member.getName());
    }
}