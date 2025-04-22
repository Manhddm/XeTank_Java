package view.rendering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import model.entities.Player;
import model.interfaces.IEntity;
import core.GameConstants;
import model.interfaces.IGameModel;
import view.renderers.PlayerRenderer;
public class GamePanel extends JPanel implements ActionListener {
    private Timer gameTimer;
    private IGameModel gameModel;
    private PlayerRenderer playerRenderer;
    public GamePanel(IGameModel gameModel) {
        this.gameModel = gameModel;
        this.playerRenderer = new PlayerRenderer();
        setPreferredSize(new Dimension(GameConstants.GAME_SCREEN_WIDTH,GameConstants.GAME_SCREEN_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setDoubleBuffered(true);
        setFocusable(true);
        int delay  = 1000/60;
        gameTimer = new Timer(delay,this);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (gameModel == null){

        }
        List<? extends IEntity> currentEntities = gameModel.getAllEntities();
        // Vẽ tất cả các entities từ model
        if (currentEntities != null) {
            // Tạo bản sao để tránh lỗi nếu danh sách bị thay đổi trong lúc vẽ
            List<? extends IEntity> entitiesToRender = new ArrayList<>(currentEntities);
            for (IEntity entity : entitiesToRender) {
                // Xác định loại entity và gọi renderer tương ứng
                if (entity instanceof Player) {
                    if (playerRenderer != null) {
                        playerRenderer.render(g2d, entity);
                    }
                }
                // else if (entity instanceof Wall) { // Ví dụ nếu có WallRenderer
                //     if (wallRenderer != null) {
                //         wallRenderer.render(g2d, entity);
                //     }
                // }
                // else if (entity instanceof Water) { // Ví dụ nếu có WaterRenderer
                //     if (waterRenderer != null) {
                //         waterRenderer.render(g2d, entity);
                //     }
                // }
                // ... thêm các loại entity và renderer khác
                else {
                    // Nếu không có renderer cụ thể, có thể vẽ mặc định hoặc bỏ qua
                    entity.draw(g2d); // Gọi phương thức draw cơ bản của entity (nếu có)
                }
            }
        } else {
            g2d.setColor(Color.YELLOW);
            g2d.drawString("Entity list is null in GameModel!", 100, 120);
        }


        // Bỏ dòng vẽ player tạm thời cứng nhắc đi
        // playerRenderer.render(g2d);


        // TODO: Vẽ các thành phần UI khác (điểm số, máu...) có thể gọi UIRenderer ở đây

        // Dispose đối tượng graphics bản sao
        g2d.dispose();
    }
    public void startGame() {
        if(gameTimer != null) {
            gameTimer.start();
            System.out.println("Game started");
        }
    }
    public void stopGame() {
        if(gameTimer != null) {
            gameTimer.stop();
            System.out.println("Game stopped");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameModel != null) {
            gameModel.update();
        }
        repaint();
    }

}