import javax.swing.SwingUtilities; // Để chạy UI trên Event Dispatch Thread

// Import các thành phần MVC
import model.GameModel;
import model.interfaces.IGameModel;
import view.rendering.GamePanel;
import view.rendering.GameWindow;
import controller.KeyboardController;
import controller.GameController; // Import GameController đầy đủ
import controller.CollisionController; // Import CollisionController
import controller.interfaces.IGameController;
import controller.interfaces.IInputController;
import controller.interfaces.ICollisionController;

import java.awt.event.KeyListener;


public class Main {
    public static void main(String[] args) {
        // Chạy trên Event Dispatch Thread (EDT) để đảm bảo an toàn luồng cho Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("--- Bắt đầu khởi tạo Game ---");

                // --- 1. Khởi tạo Model ---
                System.out.println("Khởi tạo Model...");
                GameModel gameModel = new GameModel();
                try {
                    gameModel.initialize();
                    System.out.println("GameModel đã khởi tạo thành công.");
                } catch (Exception e) {
                    System.err.println("Lỗi nghiêm trọng khi khởi tạo GameModel!");
                    e.printStackTrace();
                    return; // Thoát nếu không khởi tạo được model
                }

                // --- 2. Khởi tạo View ---
                System.out.println("Khởi tạo View (GamePanel, GameWindow)...");
                // Tạo GamePanel và truyền GameModel vào
                GamePanel gamePanel = new GamePanel(gameModel);
                System.out.println("GamePanel đã được tạo.");

                // Tạo cửa sổ chính và thêm GamePanel vào
                GameWindow gameWindow = new GameWindow(gamePanel);
                System.out.println("GameWindow đã được tạo.");

                // --- 3. Khởi tạo Controllers ---
                System.out.println("Khởi tạo Controllers (Input, Collision, Game)...");
                // Tạo KeyboardController để xử lý input
                KeyboardController keyboardController = new KeyboardController();
                keyboardController.initialize(); // Reset trạng thái phím
                System.out.println("KeyboardController đã được tạo và khởi tạo.");

                // Tạo CollisionController
                ICollisionController collisionController = new CollisionController();
                System.out.println("CollisionController đã được tạo.");

                // Tạo GameController chính
                GameController gameController = new GameController();
                gameController.setGamePanel(gamePanel);

                // Kết nối Controller với Model và Input/Collision Controllers
                gameController.setModel(gameModel);
                ((GameController) gameController).setInputController(keyboardController); // Ép kiểu nếu cần gọi phương thức cụ thể

                // Thêm KeyListener vào GamePanel (Panel cần focusable=true)
                gamePanel.addKeyListener((KeyListener) keyboardController); // Ép kiểu KeyboardController thành KeyListener

                // Khởi tạo GameController (ví dụ: tạo Timer)
                gameController.initialize();


                // --- 5. Hiển thị cửa sổ và Bắt đầu Game ---
                gameWindow.setVisible(true); // Hiển thị cửa sổ

                // Yêu cầu focus sau khi cửa sổ hiển thị
                gamePanel.requestFocusInWindow();
                System.out.println("Yêu cầu focus cho GamePanel.");

                // Bắt đầu game loop do GameController quản lý
                gameController.startGame();
                gamePanel.repaint();
                System.out.println("GameController đã bắt đầu game loop.");

                System.out.println("--- Game đã sẵn sàng và đang chạy! ---");

            }
        });
    }
}
