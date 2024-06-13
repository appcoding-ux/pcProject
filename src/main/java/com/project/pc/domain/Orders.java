package com.project.pc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member", "orderFood"})
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNum;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @Builder.Default
    private Set<OrderFood> orderFood = new HashSet<>();

    private int totalPrice;

    private String orderRequest;

    public void addOrderFood(Food food, int foodAmount){
        OrderFood memberSelectFood = OrderFood.builder()
                .food(food)
                .foodAmount(foodAmount)
                .orders(this)
                .build();

        orderFood.add(memberSelectFood);
    }
}

