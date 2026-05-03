package test;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DiaryUI extends JFrame {
    private JTextField titleField;
    private JTextArea contentArea;
    private DefaultListModel<String> listModel;
    private JList<String> diaryList;
    private DiaryDAO dao;

    public DiaryUI() {
        dao = new DiaryDAO();
        setTitle("My Pretty Diary");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 240, 245)); // LavenderBlush
        setLayout(new BorderLayout(10, 10));

        // 상단: 타이틀 및 아이콘
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255, 182, 193)); // LightPink
        JLabel titleLabel = new JLabel("Daily Journal", JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        try {
            ImageIcon icon = new ImageIcon("src/test/diary_icon.png");
            Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            titleLabel.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            System.out.println("Icon not found.");
        }
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // 중앙: 일기 작성 및 목록
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(200);

        // 왼쪽: 일기 목록
        listModel = new DefaultListModel<>();
        diaryList = new JList<>(listModel);
        diaryList.setBackground(new Color(255, 250, 240)); // FloralWhite
        JScrollPane listScroll = new JScrollPane(diaryList);
        splitPane.setLeftComponent(listScroll);

        // 오른쪽: 일기 작성 폼
        JPanel formPanel = new JPanel(new BorderLayout(5, 5));
        formPanel.setBackground(new Color(255, 240, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleField = new JTextField();
        titleField.setBorder(BorderFactory.createTitledBorder("Title"));
        titleField.setBackground(new Color(255, 255, 255));

        contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setBorder(BorderFactory.createTitledBorder("Content"));
        JScrollPane contentScroll = new JScrollPane(contentArea);

        JButton saveBtn = new JButton("Save Memory");
        saveBtn.setBackground(new Color(255, 105, 180)); // HotPink
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setOpaque(true);
        saveBtn.setBorderPainted(false);
        saveBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        saveBtn.addActionListener(e -> saveDiary());

        formPanel.add(titleField, BorderLayout.NORTH);
        formPanel.add(contentScroll, BorderLayout.CENTER);
        formPanel.add(saveBtn, BorderLayout.SOUTH);

        splitPane.setRightComponent(formPanel);
        add(splitPane, BorderLayout.CENTER);

        loadDiaries();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveDiary() {
        String title = titleField.getText();
        String content = contentArea.getText();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            return;
        }

        try {
            dao.insert(new DiaryDTO(title, content));
            titleField.setText("");
            contentArea.setText("");
            loadDiaries();
            JOptionPane.showMessageDialog(this, "Memory Saved!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving to DB: " + e.getMessage());
        }
    }

    private void loadDiaries() {
        try {
            listModel.clear();
            List<DiaryDTO> list = dao.findAll();
            for (DiaryDTO dto : list) {
                listModel.addElement(dto.getTitle() + " (" + dto.getCreatedAt() + ")");
            }
        } catch (Exception e) {
            System.err.println("Could not load diaries: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiaryUI());
    }
}
