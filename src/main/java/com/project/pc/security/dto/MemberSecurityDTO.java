package com.project.pc.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User implements OAuth2User {

    private String id;

    private String password;

    private String email;

    private String name;

    private String phone;

    private boolean del;

    private boolean social;

    private LocalTime time;

    private Map<String, Object> props;

    public MemberSecurityDTO(String username, String password, String email, String name, String phone,boolean del, boolean social, LocalTime time, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);

        this.id = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.del = del;
        this.social = social;
        this.time = time;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.getProps();
    }
}
