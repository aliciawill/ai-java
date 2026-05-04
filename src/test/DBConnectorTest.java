package test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBConnector.java의 연결 기능을 테스트하기 위한 TDD 스타일의 테스트 클래스입니다.
 * 별도의 테스팅 프레임워크(JUnit 등) 없이 실행 가능하도록 main 메서드를 포함합니다.
 */
public class DBConnectorTest {

    public static void main(String[] args) {
        System.out.println("========== DBConnector Test Start ==========");
        
        boolean isSuccess = testGetConnection();
        
        if (isSuccess) {
            System.out.println("Result: [PASS] DB 연결 테스트 성공");
        } else {
            System.out.println("Result: [FAIL] DB 연결 테스트 실패");
            System.exit(1); // 테스트 실패 시 비정상 종료
        }
        
        System.out.println("========== DBConnector Test End ==========");
    }

    /**
     * DBConnector.getConnection() 메서드가 유효한 Connection 객체를 반환하는지 테스트합니다.
     * @return 성공 여부
     */
    public static boolean testGetConnection() {
        System.out.print("Testing DB connection... ");
        
        try (Connection conn = DBConnector.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("OK");
                // 연결된 DB 정보 출력 (선택 사항)
                System.out.println(" - DB URL: " + conn.getMetaData().getURL());
                System.out.println(" - DB User: " + conn.getMetaData().getUserName());
                return true;
            } else {
                System.out.println("FAILED (Connection is null or closed)");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("FAILED");
            System.err.println("Error: " + e.getMessage());
            // TDD 관점에서 구체적인 에러 원인 파악을 위해 스택 트레이스 출력
            e.printStackTrace();
            return false;
        }
    }
}
