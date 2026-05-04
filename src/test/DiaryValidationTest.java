package test;

import java.sql.SQLException;
import java.util.List;

/**
 * DiaryUI의 저장 로직과 DAO의 빈 값 처리 능력을 테스트하는 TDD 클래스입니다.
 * 1. DAO 레벨에서 빈 값이 DB에 삽입되는지 확인
 * 2. UI 레벨의 검증 로직이 필요한 이유를 증명
 */
public class DiaryValidationTest {

    public static void main(String[] args) {
        System.out.println("========== Diary Save Validation Test Start ==========");
        
        testDaoWithEmptyValues();
        
        System.out.println("========== Diary Save Validation Test End ==========");
    }

    /**
     * DAO를 통해 제목과 내용이 비어있는 DTO를 강제로 삽입해봅니다.
     * 결과가 성공한다면, DB 자체적으로는 빈 값을 허용하고 있다는 뜻이며,
     * 이는 UI(DiaryUI.java)에서 필수로 체크를 해줘야 함을 시사합니다.
     */
    public static void testDaoWithEmptyValues() {
        DiaryDAO dao = new DiaryDAO();
        System.out.println("[Test 1] Testing DAO insertion with EMPTY strings...");
        
        try {
            // 빈 문자열을 가진 DTO 생성
            DiaryDTO emptyDto = new DiaryDTO("", "");
            
            // 삽입 전 데이터 개수 확인
            int beforeSize = dao.findAll().size();
            
            // 삽입 시도
            dao.insert(emptyDto);
            
            // 삽입 후 데이터 개수 확인
            List<DiaryDTO> list = dao.findAll();
            int afterSize = list.size();
            
            if (afterSize > beforeSize) {
                System.out.println(" -> Result: [SUCCESS] Empty values WERE inserted into the database.");
                System.out.println(" -> Observation: The database/DAO does NOT block empty strings automatically.");
                System.out.println(" -> Conclusion: DiaryUI.java's validation (title.isEmpty() check) is CRITICAL.");
            } else {
                System.out.println(" -> Result: [FAIL] Data size did not increase.");
            }
            
        } catch (SQLException e) {
            System.out.println(" -> Result: [BLOCKED] Database blocked empty strings: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" -> Error during test: " + e.getMessage());
        }
    }
}
