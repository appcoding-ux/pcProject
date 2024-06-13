package com.project.pc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private boolean del;

    private boolean social;

    @Builder.Default
    private LocalTime time = LocalTime.of(0, 0, 0);

    @ElementCollection
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String password){
        this.password = password;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changeDel(boolean del){
        this.del = del;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void addTime(Long paymentHour){
        this.time = this.time.plusHours(paymentHour);
    }

    public void updateTime(){
        this.time = this.time.minusMinutes(1);
    }

    public void userDelete(){
        this.roleSet = null;
    }
}
