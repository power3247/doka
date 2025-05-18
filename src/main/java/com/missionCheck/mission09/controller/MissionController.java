package com.missionCheck.mission09.controller;

import com.missionCheck.mission09.entity.Mission;
import com.missionCheck.mission09.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final Map<LocalDate, List<Mission>> missionStore = new HashMap<>();

    private final List<String> baseMissions = new ArrayList<>(List.of(
            "3분 스트레칭", "물 마시기", "휴대폰 내려두기", "책 1쪽 읽기", "할 일 써보기",
            "심호흡 3번", "일기 5줄", "똑바로 앉기", "눈 감고 10초 집중", "100m 달리기",
            "팔 벌려 뛰기 10회", "눈 감고 호흡 집중", "손목 돌리기", "발끝 들기 20회",
            "좋은 말 한마디 적기", "욕 안 하기 하루", "소리 지르지 않기", "친절한 댓글 달기",
            "쓰레기 하나 줍기", "앉아서 복식호흡 1분", "밝은 표정 연습", "창문 열고 환기하기",
            "감사한 일 1개 쓰기", "5분 산책", "자세 바로잡기", "물건 하나 정리하기",
            "불필요한 앱 삭제하기", "메일함 비우기", "사진 정리하기", "노래 듣기",
            "차 한 잔 마시기", "거울 보고 미소 짓기", "하늘 보기", "구름 구경하기",
            "오늘 목표 적기", "5분 눈 감기", "손톱 깎기", "양치하기", "발 마사지",
            "가벼운 스트레칭", "노트 필기 정리", "단어 암기 5개", "프랑스어 인사 연습",
            "뉴스 헤드라인 1개 읽기", "30초 플랭크", "다리 올리기 운동", "엉덩이 근육 조이기",
            "스쿼트 5개", "걱정 1개 적고 덜어내기", "내일 할 일 미리 정하기"
    ));

    @PostMapping("/generate")
    public void generateTodayMissions() {
        LocalDate today = LocalDate.now();
        if (missionStore.containsKey(today)) return;

        List<Mission> todayMissions = new ArrayList<>();

        Collections.shuffle(baseMissions); // 랜덤 섞기
        for (int i = 0; i < 5; i++) {
            todayMissions.add(new Mission(
                    (long) i,
                    baseMissions.get(i),
                    today,
                    false
            ));
        }

        missionStore.put(today, todayMissions);
    }

    @PostMapping("/base")
    public void addBaseMission(@RequestBody Map<String, String> request) {
        String newMission = request.get("content");

        // 중복 방지 (선택 사항)
        if (!baseMissions.contains(newMission)) {
            baseMissions.add(newMission);
        }
    }

    @GetMapping("/today")
    public List<Mission> getTodayMissions() {
        return missionStore.getOrDefault(LocalDate.now(), List.of());
    }

    @PatchMapping("/{id}/toggle")
    public void toggleMission(@PathVariable Long id) {
        List<Mission> missions = missionStore.get(LocalDate.now());
        if (missions == null) return;
        missions.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .ifPresent(m -> m.setCompleted(!m.isCompleted()));
    }

    @DeleteMapping("/{id}")
    public void deleteMission(@PathVariable Long id) {
        List<Mission> missions = missionStore.get(LocalDate.now());
        if (missions != null) {
            missions.removeIf(m -> m.getId().equals(id));
        }
    }
}