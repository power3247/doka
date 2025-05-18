package com.missionCheck.mission09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Mission {
    private Long id;
    private String content;
    private LocalDate date;
    private boolean completed;
}
