package view.rendering;

import javax.swing.*;
import java.awt.*; // Thêm import Dimension

public class GameWindow extends JFrame {

    // Nhận JPanel chính (GameView) từ bên ngoài
    public GameWindow(JPanel mainGamePanel) {
        setTitle("Xe Tank Game"); // Đặt tiêu đề
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Thêm GameView (đã được khởi tạo và liên kết) vào cửa sổ
        add(mainGamePanel);

        pack(); // Tự động điều chỉnh kích thước cửa sổ dựa trên preferredSize của panel
        setLocationRelativeTo(null); // Hiển thị cửa sổ giữa màn hình
    }


}