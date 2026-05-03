package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


//클래스명 자동 생성
public class SimpleCalculator extends JFrame {
    private JTextField num1Field;
    private JTextField num2Field;

    public SimpleCalculator() {
        setTitle("Colorful Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255)); // AliceBlue 배경
        setLayout(new BorderLayout(10, 10));

        // 상단: 이미지 및 타이틀
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(100, 149, 237)); // CornflowerBlue
        JLabel titleLabel = new JLabel("My Calculator", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));

        // 이미지 추가 (파일이 없을 경우 대비하여 체크)
        try {
            ImageIcon icon = new ImageIcon("src/test/calc_icon.png");
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            titleLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println("Image not found, skipping icon.");
        }

        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // 중앙: 입력 필드
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.setBackground(new Color(240, 248, 255));

        JLabel lbl1 = new JLabel("Number 1:");
        lbl1.setFont(new Font("SansSerif", Font.BOLD, 14));
        num1Field = new JTextField();
        num1Field.setBackground(new Color(255, 255, 224)); // LightYellow

        JLabel lbl2 = new JLabel("Number 2:");
        lbl2.setFont(new Font("SansSerif", Font.BOLD, 14));
        num2Field = new JTextField();
        num2Field.setBackground(new Color(255, 255, 224));

        centerPanel.add(lbl1);
        centerPanel.add(num1Field);
        centerPanel.add(lbl2);
        centerPanel.add(num2Field);
        add(centerPanel, BorderLayout.CENTER);

        // 하단: 버튼들
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        bottomPanel.setBackground(new Color(240, 248, 255));

        String[] ops = {"+", "-", "*", "/"};
        Color[] btnColors = {new Color(255, 182, 193), new Color(173, 216, 230),
                             new Color(144, 238, 144), new Color(255, 218, 185)};

        for (int i = 0; i < ops.length; i++) {
            JButton btn = new JButton(ops[i]);
            btn.setBackground(btnColors[i]);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.addActionListener(new CalcActionListener());
            bottomPanel.add(btn);
        }
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // 화면 중앙 배치
        setVisible(true);
    }

    private class CalcActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double n1 = Double.parseDouble(num1Field.getText());
                double n2 = Double.parseDouble(num2Field.getText());
                double result = 0;
                String op = e.getActionCommand();

                switch (op) {
                    case "+": result = n1 + n2; break;
                    case "-": result = n1 - n2; break;
                    case "*": result = n1 * n2; break;
                    case "/":
                        if (n2 == 0) {
                            showResult("Error: Cannot divide by zero!", "Math Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        result = n1 / n2;
                        break;
                }
                showResult("The result is: " + result, "Calculation Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                showResult("Error: Please enter valid numbers!", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void showResult(String message, String title, int messageType) {
        // 작은 창(JOptionPane)으로 결과 표시
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator());
    }
}
