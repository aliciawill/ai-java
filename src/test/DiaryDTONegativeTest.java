package test;

import java.sql.Timestamp;

/**
 * DiaryDTO.java의 예외 상황 및 경계값 등을 테스트하기 위한 TDD 스타일의 테스트 클래스입니다.
 * (Negative Testing)
 */
public class DiaryDTONegativeTest {

    public static void main(String[] args) {
        System.out.println("========== DiaryDTO Negative Test Start ==========");
        
        boolean success = true;
        success &= testDefaultValues();
        success &= testNullValues();
        success &= testExtremeValues();
        success &= testInstanceIndependence();
        
        if (success) {
            System.out.println("Result: [PASS] DiaryDTO 모든 예외 케이스 테스트 성공");
        } else {
            System.out.println("Result: [FAIL] DiaryDTO 테스트 중 예상치 못한 결과 발생");
            System.exit(1);
        }
        
        System.out.println("========== DiaryDTO Negative Test End ==========");
    }

    /**
     * 초기화되지 않은 DTO의 기본값을 확인합니다.
     */
    public static boolean testDefaultValues() {
        System.out.print("Testing Default Values... ");
        DiaryDTO dto = new DiaryDTO();
        
        // int는 0, 객체(String, Timestamp)는 null이어야 함
        if (dto.getId() == 0 && dto.getTitle() == null && 
            dto.getContent() == null && dto.getCreatedAt() == null) {
            System.out.println("OK");
            return true;
        } else {
            System.out.println("FAILED (Default values are not as expected)");
            return false;
        }
    }

    /**
     * 필드에 null 값을 설정했을 때의 동작을 확인합니다.
     */
    public static boolean testNullValues() {
        System.out.print("Testing Null Values... ");
        DiaryDTO dto = new DiaryDTO("Title", "Content");
        
        dto.setTitle(null);
        dto.setContent(null);
        dto.setCreatedAt(null);
        
        if (dto.getTitle() == null && dto.getContent() == null && dto.getCreatedAt() == null) {
            System.out.println("OK");
            return true;
        } else {
            System.out.println("FAILED (Null was not handled correctly)");
            return false;
        }
    }

    /**
     * 빈 문자열이나 큰 숫자 등 경계값을 테스트합니다.
     */
    public static boolean testExtremeValues() {
        System.out.print("Testing Extreme Values... ");
        DiaryDTO dto = new DiaryDTO();
        
        String empty = "";
        int maxId = Integer.MAX_VALUE;
        
        dto.setTitle(empty);
        dto.setId(maxId);
        
        if (empty.equals(dto.getTitle()) && dto.getId() == maxId) {
            System.out.println("OK");
            return true;
        } else {
            System.out.println("FAILED");
            return false;
        }
    }

    /**
     * 서로 다른 인스턴스가 독립적인지 확인합니다.
     */
    public static boolean testInstanceIndependence() {
        System.out.print("Testing Instance Independence... ");
        DiaryDTO dto1 = new DiaryDTO("Title1", "Content1");
        DiaryDTO dto2 = new DiaryDTO("Title2", "Content2");

        dto1.setTitle("Changed");

        // dto1을 변경했을 때 dto2가 영향을 받지 않아야 함
        if (!dto1.getTitle().equals(dto2.getTitle())) {
            System.out.println("OK");
            return true;
        } else {
            System.out.println("FAILED (Instances are linked)");
            return false;
        }
    }
}
