package com.missionCheck.mission09.service;

import com.missionCheck.mission09.entity.Mission;
import com.missionCheck.mission09.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public void generateMissions() {
        LocalDate today = LocalDate.now();
        if (!missionRepository.findAllByDate(today).isEmpty()) return;

        List<String> contents = List.of(
                "3분 스트레칭 하기", "물 한 컵 마시기", "휴대폰 내려두기",
                "책 1쪽 읽기", "오늘 할 일 써보기", "심호흡 3번 하기",
                "5줄 일기 쓰기", "의자에 똑바로 앉기", "눈 감고 10초 집중하기"
        );

        List<Mission> missions = contents.stream()
                .map(content -> Mission.builder()
                        .content(content)
                        .date(today)
                        .completed(false)
                        .build())
                .toList();

        missionRepository.saveAll(missions);
    }

    public List<Mission> getTodayMissions() {
        return missionRepository.findAllByDate(LocalDate.now());
    }

    public void toggleCompleted(Long id) {
        Mission mission = missionRepository.findById(id).orElseThrow();
        mission.setCompleted(!mission.isCompleted());
        missionRepository.save(mission);
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}
