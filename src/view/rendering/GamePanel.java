package view.rendering;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import model.entities.Player;
import model.entities.Wall; // Import Wall
import model.interfaces.IEntity;
import core.GameConstants;
import model.interfaces.IGameModel;
import view.interfaces.IGameView;
import view.renderers.PlayerRenderer;
import view.renderers.WallRenderer; // Import WallRenderer (nếu có)

// Bỏ implements ActionListener
public class GamePanel extends JPanel implements IGameView {
    // private Timer gameTimer; // Xóa bỏ timer
    private IGameModel gameModel; // Giữ tham chiếu đến model

    // Các renderer cụ thể (có thể quản lý bằng cách khác, ví dụ Map)
    private PlayerRenderer playerRenderer;
    private WallRenderer wallRenderer; // Thêm WallRenderer (nếu có)
    // Thêm các renderer khác nếu cần (Water, Grass, Bullet...)

    public GamePanel(IGameModel gameModel) {
        this.gameModel = gameModel;

        // Khởi tạo các renderer
        this.playerRenderer = new PlayerRenderer();
        this.wallRenderer = new WallRenderer(); // Khởi tạo WallRenderer
        // Khởi tạo các renderer khác...

        setPreferredSize(new Dimension(GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT));
        setBackground(Color.DARK_GRAY); // Màu nền tối cho dễ nhìn tank
        setDoubleBuffered(true); // Giúp vẽ mượt hơn
        setFocusable(true); // **Rất quan trọng** để nhận sự kiện bàn phím

        // Xóa bỏ khởi tạo và bắt đầu timer
        // int delay  = 1000/60;
        // gameTimer = new Timer(delay,this);
        System.out.println("GamePanel created and configured.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Gọi phương thức vẽ của lớp cha (quan trọng)
        Graphics2D g2d = (Graphics2D) g.create(); // Tạo bản sao context để tránh ảnh hưởng lẫn nhau
        if (gameModel == null) {
            // Vẽ thông báo lỗi nếu model chưa được set
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString("Lỗi: GameModel chưa được thiết lập!", 50, 50);
            g2d.dispose();
            return;
        }

        // Lấy danh sách thực thể hiện tại từ model
        List<? extends IEntity> currentEntities = gameModel.getAllEntities();

        // Vẽ tất cả các thực thể từ model
        if (currentEntities != null) {
            // Tạo bản sao để tránh lỗi ConcurrentModificationException
            // nếu danh sách bị thay đổi trong lúc vẽ (ví dụ: đạn được tạo ra)
            List<? extends IEntity> entitiesToRender = new ArrayList<>(currentEntities);

            for (IEntity entity : entitiesToRender) {
                if (entity == null) continue; // Bỏ qua nếu entity bị null

                // Xác định loại entity và gọi renderer tương ứng
                if (entity instanceof Player) {
                    System.out.println("co player");
                    if (playerRenderer != null) {
                        playerRenderer.render(g2d, entity);
                    } else {
                        entity.draw(g2d); // Vẽ mặc định nếu renderer null
                    }
                } else if (entity instanceof Wall) {
                    // Nếu có WallRenderer riêng:
                    if (wallRenderer != null) {
                        wallRenderer.render(g2d, entity);
                    } else {
                        entity.draw(g2d); // Gọi draw() của Wall nếu không có renderer
                    }
                    // Hoặc chỉ cần gọi draw() nếu Wall tự vẽ được:
                    // entity.draw(g2d);
                }
                // else if (entity instanceof Water) { ... }
                // else if (entity instanceof Grass) { ... }
                // else if (entity instanceof Bullet) { ... }
                else {
                    // Vẽ mặc định cho các loại entity khác chưa có renderer riêng
                    entity.draw(g2d);
                }
            }
        } else {
            // Vẽ thông báo nếu danh sách entity là null (ít xảy ra nếu model khởi tạo đúng)
            g2d.setColor(Color.YELLOW);
            g2d.drawString("Danh sách thực thể là null trong GameModel!", 100, 120);
        }

        // TODO: Vẽ các thành phần UI khác (điểm số, máu...) có thể gọi UIRenderer ở đây

        // Giải phóng context bản sao
        g2d.dispose();
    }

    // Xóa bỏ các phương thức liên quan đến timer
    // public void startGame() { ... }
    // public void stopGame() { ... }
    // @Override
    // public void actionPerformed(ActionEvent e) { ... }

    // Thêm phương thức để GameController có thể yêu cầu vẽ lại khi cần
    // (Mặc dù thường không cần thiết vì Swing sẽ tự động repaint khi trạng thái thay đổi)
    public void triggerRepaint() {
        repaint();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void setModel(IGameModel model) {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void showGameOverScreen(int winnerIndex) {

    }

    @Override
    public void showMainMenu() {

    }

    @Override
    public void dispose() {

    }
}
