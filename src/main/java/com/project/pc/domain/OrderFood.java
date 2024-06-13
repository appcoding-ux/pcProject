package com.project.pc.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"food", "orders"})
public class OrderFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderFoodNum;

    @ManyToOne
    private Food food;

    private int foodAmount;

    @ManyToOne
    private Orders orders;
}
