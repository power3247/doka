package com.missionCheck.mission09.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id @GeneratedValue
    private Long id;

    private String content;

    private LocalDate date;

    private boolean completed;
}