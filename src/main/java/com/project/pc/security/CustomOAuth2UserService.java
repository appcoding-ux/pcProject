package com.project.pc.security;

import com.project.pc.domain.Member;
import com.project.pc.domain.MemberRole;
import com.project.pc.security.dto.MemberSecurityDTO;
import com.project.pc.repository.MemberRepository;
import com.project.pc.service.kakaoTalk.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest :" + userRequest.getAccessToken());

        // 나에게 카카오톡 보내기
        MessageService.authToken = userRequest.getAccessToken().getTokenValue();
        System.out.println(MessageService.authToken);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        String clientName = clientRegistration.getClientName();

        log.info("NAME : " + clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        String name = null;

        switch (clientName) {
            case "kakao" :
                email = getKakaoEmail(paramMap);
                name = getKakaoName(paramMap);
                break;
        }

        return generateDTO(email, name, paramMap);
    }

    private MemberSecurityDTO generateDTO(String email, String name, Map<String, Object> paramMap){
        Optional<Member> result = memberRepository.findByEmail(email);

        if(result.isEmpty()){
            Member member = Member.builder()
                    .id(email)
                    .password("")
                    .email(email)
                    .name(name)
                    .phone("")
                    .social(true)
                    .build();

            member.addRole(MemberRole.USER);
            memberRepository.save(member);

            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(email, "", email, name, "",false, true, LocalTime.of(0,0), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            memberSecurityDTO.setProps(paramMap);

            return memberSecurityDTO;
        }else {
            Member member = result.get();

            return new MemberSecurityDTO(member.getId(), member.getPassword(), member.getEmail(), member.getName(), "" ,member.isDel(), member.isSocial(), member.getTime() ,member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name())).collect(Collectors.toList()));
        }
    }

    private  String getKakaoEmail(Map<String, Object> paramMap) {
        Object value = paramMap.get("kakao_account");

        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String)accountMap.get("email");

        log.info("email : " + email);

        return email;
    }

    private String getKakaoName(Map<String, Object> paramMap){
        Object value = paramMap.get("kakao_account");

        LinkedHashMap accountMap = (LinkedHashMap) value;

        LinkedHashMap<String, String> profileMap = (LinkedHashMap<String, String>) accountMap.get("profile");

        log.info(profileMap.get("nickname"));

        return profileMap.get("nickname");
    }
}
