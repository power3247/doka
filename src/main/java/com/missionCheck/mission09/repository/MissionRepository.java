package com.missionCheck.mission09.repository;

import com.missionCheck.mission09.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByDate(LocalDate date);
}
