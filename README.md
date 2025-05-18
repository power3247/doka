# Mission9 (찝찝 미션 API)

💡 하루에 5개의 작고 귀찮은 '찝찝 미션'을 랜덤으로 추천받고, 완료 여부를 기록하는 아주 가벼운 **Spring Boot REST API 학습용 프로젝트**입니다.

---

## ✨ Features

- 50개 이상의 미션 중 **5개를 랜덤 추천**
- 미션 완료 여부 토글 가능
- 미션 삭제 가능
- 기본 미션 목록에 **새로운 미션 추가 가능**
- **DB 없이 메모리 기반**으로 동작 (간단하고 빠름)

---

## 🧰 Tech Stack

- Java 17
- Spring Boot 3.4.5
- Spring Web
- Lombok
- (H2 / JPA 없이 동작)

---

## 🚀 Getting Started

### 1. 프로젝트 실행
```bash
./gradlew bootRun


2. 테스트 도구  
Postman 

📦 API Endpoints
✅ 오늘 미션 생성
bash
복사
편집
POST /api/missions/generate
오늘의 랜덤 미션 5개 생성 (한 번만 실행)

📋 오늘 미션 목록 조회
bash
복사
편집
GET /api/missions/today
오늘 생성된 미션 5개 목록 반환

🔄 미션 완료 토글
bash
복사
편집
PATCH /api/missions/{id}/toggle
해당 미션의 완료 상태를 반전 (완료 ↔ 미완료)

❌ 미션 삭제
bash
복사
편집
DELETE /api/missions/{id}
해당 ID의 미션을 삭제

➕ 기본 미션 목록에 새 항목 추가
css
복사
편집
POST /api/missions/base
Body: { "content": "새로운 미션 텍스트" }
추후 랜덤 추천 대상이 됨

🗂 구조 (간단)
복사
편집
📁 model
  └─ Mission.java

📁 controller
  └─ MissionController.java
📌 Notes
서버를 재시작하면 데이터는 초기화됩니다 (DB 없이 메모리 기반)

학습/테스트/PoC 용도로 적합합니다

확장 예: 파일 저장, 유저 인증, 포인트 시스템 등

🧑‍💻 Author
made by [DOKA]

GitHub: https://github.com/power3247/missionGenerater
