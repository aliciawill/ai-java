package test;

import java.sql.Timestamp;

public class DiaryDTO {
    private int id;
    private String title;
    private String content;
    private Timestamp createdAt;

    public DiaryDTO() {}

    public DiaryDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
