package com.project.pc.service.member;

import com.project.pc.domain.Member;
import com.project.pc.domain.MemberRole;
import com.project.pc.dto.MemberDTO;
import com.project.pc.dto.PageRequestDTO;
import com.project.pc.dto.PageResponseDTO;
import com.project.pc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void memberInsert(MemberDTO memberDTO) {
        Member member = Member.builder()
                .phone(memberDTO.getPhone())
                .del(false)
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .id(memberDTO.getId())
                .social(false)
                .build();

        member.changePassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.addRole(MemberRole.USER);

        memberRepository.save(member);
    }

    @Override
    public MemberDTO idCheck(String id) {
        Optional<Member> result = memberRepository.findById(id);

        if(result.isEmpty()){
            return null;
        }else {
            Member member = result.get();

            return modelMapper.map(member, MemberDTO.class);
        }
    }

    @Override
    public MemberDTO findMember(String id) {
        return modelMapper.map(memberRepository.findById(id).orElseThrow(), MemberDTO.class);
    }

    @Override
    public boolean emailCheck(String email) {
        String check = memberRepository.emailCheck(email);

        if(check != null){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public void addTime(Long paymentHour, String id) {
        Member member = memberRepository.findById(id).orElseThrow();

        member.addTime(paymentHour);

        memberRepository.save(member);
    }

    @Override
    public void updateTime(String id) {
        Member member = memberRepository.findById(id).orElseThrow();

        member.updateTime();

        memberRepository.save(member);
    }

    @Override
    public void removeMember(String id) {
        Member member = memberRepository.findById(id).orElseThrow();

        member.userDelete();

        memberRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<MemberDTO> listWithAll(PageRequestDTO pageRequestDTO) {
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        Page<Member> result = memberRepository.searchWithAll(keyword, pageable);

        List<MemberDTO> list = result.getContent().stream().map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<MemberDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(list)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void updatePass(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        member.changePassword(passwordEncoder.encode(password));

        memberRepository.save(member);
    }
}
