# Diary Application Project

## Overview
Java Swing 기반의 MySQL 연동 일기장 프로그램입니다.

## Architecture
- **DTO (Data Transfer Object)**: `DiaryDTO.java` - 일기 데이터를 담는 가방 역할을 합니다.
- **DB Connection**: `DBConnector.java` - MySQL 데이터베이스 연결을 관리합니다.
- **DAO (Data Access Object)**: `DiaryDAO.java` - 데이터베이스 CRUD(생성, 읽기, 수정, 삭제) 작업을 수행합니다.
- **UI (User Interface)**: `DiaryUI.java` - Swing을 이용한 사용자 화면을 구성하며, 비즈니스 로직과 분리되어 있습니다.

## Conventions
- **Naming**: 클래스명은 PascalCase, 변수 및 메서드명은 camelCase를 사용합니다.
- **Cohesion**: 각 클래스는 고유의 책임에 집중하며, 로직 간의 결합도를 낮춥니다.
- **Aesthetics**: `UIManager`와 `Color` 클래스를 적극 활용하여 미려한 UI를 유지합니다.
- **Images**: 아이콘 및 배경 이미지는 `src/test/resources` 또는 유사한 경로에 배치합니다.

## Execution Guide
이 프로그램은 MySQL JDBC 드라이버가 필요합니다. `lib/` 폴더에 드라이버가 다운로드되어 있습니다.

### 1. Compile
```bash
javac -d out -cp "lib/*" src/test/*.java
```

### 2. Run
```bash
# macOS / Linux
java -cp "out:lib/*" test.DiaryUI

# Windows
java -cp "out;lib/*" test.DiaryUI
```

## Database Schema (Example)
```sql
CREATE DATABASE diary_db;
USE diary_db;
CREATE TABLE entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
