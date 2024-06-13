package com.project.pc.config;

import com.project.pc.security.CustomUserDetailService;
import com.project.pc.security.handler.Custom403Handler;
import com.project.pc.security.handler.CustomSocialLoginSuccessHandler;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    private final DataSource dataSource;

    private final CustomUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 로그인 페이지로 이동 -> 만약 로그인에 실패했을 경우 이동하는 경로
        http.formLogin().loginPage("/member/login").failureUrl("/member/login?error=fail");

        // 로그인 정보 기억
        http.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailService)
                .tokenValiditySeconds(60*60*24*30);

        // 로그인에 실패했을 때
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        http.logout().logoutUrl("/logout").addLogoutHandler(((request, response, authentication) -> {
            HttpSession session = request.getSession();

            if(session != null){
                session.invalidate();
            }
        })).logoutSuccessHandler((request, response, authentication) -> {
            response.sendRedirect("/member/login?error=logout");
        }).deleteCookies("rememberMe");

        // oauth2로 로그인 설정 (카카오, 네이버, 구글 등)
        http.oauth2Login().loginPage("/member/login").successHandler(authenticationSuccessHandler());

        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }
}
