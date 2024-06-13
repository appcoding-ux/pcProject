package com.project.pc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "seat")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {

    @Id
    private Long seatNum;

    @ColumnDefault("''")
    private String id;

    public void endOfUse(){
        this.id = "";
    }

    public void loginUse(String id){
        this.id = id;
    }
}