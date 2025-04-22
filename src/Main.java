import javax.swing.SwingUtilities; // Để chạy UI trên Event Dispatch Thread

// Import các thành phần MVC
import model.GameModel;
import model.interfaces.IGameModel;
import view.rendering.GamePanel;
import view.rendering.GameWindow;
import controller.KeyboardController;
// import controller.GameController; // Sẽ cần khi hoàn thiện controller
// import controller.interfaces.IGameController; // Sẽ cần khi hoàn thiện controller

public class Main {
    public static void main(String[] args) {
        // Nên khởi tạo và chạy giao diện đồ họa trên Event Dispatch Thread (EDT)
        // để đảm bảo an toàn luồng cho Swing.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Initializing game components...");

                // --- 1. Khởi tạo Model ---
                IGameModel gameModel = new GameModel();
                try {
                    gameModel.initialize(); // Tải dữ liệu, tạo entities ban đầu (bao gồm players)
                    System.out.println("GameModel initialized.");
                } catch (Exception e) {
                    System.err.println("Failed to initialize GameModel!");
                    e.printStackTrace();
                    return; // Thoát nếu không khởi tạo được model
                }


                // --- 2. Khởi tạo View ---
                // Tạo GamePanel và truyền GameModel vào
                GamePanel gamePanel = new GamePanel(gameModel);
                System.out.println("GamePanel created.");

                // Tạo KeyboardController để xử lý input
                KeyboardController keyboardController = new KeyboardController();
                keyboardController.initialize(); // Gọi initialize nếu cần
                // Thêm KeyListener vào GamePanel (Panel cần focusable=true)
                gamePanel.addKeyListener(keyboardController);
                gamePanel.requestFocusInWindow(); // Yêu cầu focus để nhận sự kiện phím
                System.out.println("KeyboardController created and added as listener.");


                // Tạo cửa sổ chính và thêm GamePanel vào
                GameWindow gameWindow = new GameWindow(gamePanel);
                System.out.println("GameWindow created.");


                // --- 3. Khởi tạo Controller (Tạm thời) ---
                // IGameController gameController = new GameController();
                // gameController.setModel(gameModel);
                // // gameController.setView(gameView); // Cần IGameView nếu dùng
                // gameController.setInputController(keyboardController); // Cần thêm vào controller
                // gameController.initialize();
                // System.out.println("GameController initialized (partially).");


                // --- 4. Hiển thị cửa sổ và bắt đầu Game Loop ---
                gameWindow.setVisible(true); // Hiển thị cửa sổ
                System.out.println("GameWindow set visible.");

                // Bắt đầu game loop (Timer trong GamePanel)
                // Nên để Controller quản lý việc này, nhưng tạm thời gọi trực tiếp
                gamePanel.startGame();
                System.out.println("Requesting GamePanel to start game loop...");

                System.out.println("Game setup complete. Running...");
            }
        });
    }
}