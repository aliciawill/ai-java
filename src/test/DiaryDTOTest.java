package test;

import java.sql.Timestamp;

/**
 * DiaryDTO.java의 기능을 테스트하기 위한 TDD 스타일의 테스트 클래스입니다.
 */
public class DiaryDTOTest {

    public static void main(String[] args) {
        System.out.println("========== DiaryDTO Test Start ==========");
        
        boolean success = true;
        success &= testConstructorAndGetters();
        success &= testSettersAndGetters();
        
        if (success) {
            System.out.println("Result: [PASS] DiaryDTO 모든 테스트 성공");
        } else {
            System.out.println("Result: [FAIL] DiaryDTO 테스트 중 실패 발생");
            System.exit(1);
        }
        
        System.out.println("========== DiaryDTO Test End ==========");
    }

    /**
     * 생성자와 Getter 기능을 테스트합니다.
     */
    public static boolean testConstructorAndGetters() {
        System.out.print("Testing Constructor and Getters... ");
        String expectedTitle = "Test Title";
        String expectedContent = "Test Content";
        
        DiaryDTO dto = new DiaryDTO(expectedTitle, expectedContent);
        
        if (expectedTitle.equals(dto.getTitle()) && expectedContent.equals(dto.getContent())) {
            System.out.println("OK");
            return true;
        } else {
            System.out.println("FAILED");
            System.err.println(" Expected: " + expectedTitle + ", " + expectedContent);
            System.err.println(" Actual: " + dto.getTitle() + ", " + dto.getContent());
            return false;
        }
    }

    /**
     * Setter와 Getter 기능을 테스트합니다.
     */
    public static boolean testSettersAndGetters() {
        System.out.print("Testing Setters and Getters... ");
        DiaryDTO dto = new DiaryDTO();
        
        int id = 1;
        String title = "New Title";
        String content = "New Content";
        Timestamp now = new Timestamp(System.currentTimeMillis());
        
        dto.setId(id);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setCreatedAt(now);
        
        boolean match = (dto.getId() == id) &&
                        title.equals(dto.getTitle()) &&
                        content.equals(dto.getContent()) &&
                        now.equals(dto.getCreatedAt());
        
        if (match) {
            System.out.println("OK");
            return true;
        } else {
            System.out.println("FAILED");
            return false;
        }
    }
}
