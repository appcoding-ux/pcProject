package com.project.pc.security;

import com.project.pc.domain.Member;
import com.project.pc.security.dto.MemberSecurityDTO;
import com.project.pc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인하는 회원의 username : " + username);

        Optional<Member> result = memberRepository.getWithRoles(username);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("username not found...");
        }

        Member member = result.get();

        log.info("회원 이름 : " + member.getName());

        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(member.getId(), member.getPassword(), member.getEmail(), member.getName(), member.getPhone(),member.isDel(), false, member.getTime() ,member.getRoleSet().stream().map(memberRole ->  new SimpleGrantedAuthority("ROLE_"+memberRole.name())).collect(Collectors.toList()));

        log.info("로그인 한 유저의 정보 : " + memberSecurityDTO);

        return memberSecurityDTO;
    }
}
