package com.project.pc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "food")
public class FoodImage {

    @Id
    private String uuid;

    private String fileName;

    @OneToOne
    private Food food;
}
