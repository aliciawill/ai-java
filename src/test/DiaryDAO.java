package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {
    public void insert(DiaryDTO dto) throws SQLException {
        String sql = "INSERT INTO entries (title, content) VALUES (?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.executeUpdate();
        }
    }

    public List<DiaryDTO> findAll() throws SQLException {
        List<DiaryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM entries ORDER BY created_at DESC";
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DiaryDTO dto = new DiaryDTO();
                dto.setId(rs.getInt("id"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(dto);
            }
        }
        return list;
    }
}
