package view.rendering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import model.interfaces.IEntity;
import core.GameConstants;
import view.renderers.PlayerRenderer;

public class GamePanel extends JPanel implements ActionListener {

    private Timer gameTimer;
    private List<IEntity> entities;

    private PlayerRenderer playerRenderer = new PlayerRenderer();
    public GamePanel() throws IOException {
        // Thiết lập cơ bản
        setPreferredSize(new Dimension(GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);

        // Khởi tạo danh sách các entity
        entities = new ArrayList<>();

        // Thiết lập game timer (giả sử 60 FPS)
        gameTimer = new Timer(1000 / 60, this);
        gameTimer.start();
    }

    public void addEntity(IEntity entity) {
        entities.add(entity);
    }

    public void removeEntity(IEntity entity) {
        entities.remove(entity);
    }

    public void setEntities(List<IEntity> entities) {
        this.entities = entities;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Áp dụng anti-aliasing để làm mịn đồ họa
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ tất cả các entities
        for (IEntity entity : entities) {
            // Logic vẽ entity ở đây - tùy thuộc vào cách bạn cài đặt IEntity
            // Ví dụ: entity.render(g2d);
        }

        // Vẽ người chơi tạm thời
        playerRenderer.render(g2d);

        // Dispose đối tượng graphics
        g2d.dispose();
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        // Di chuyển người chơi tạm thời (cho hiệu ứng chuyển động)


        // Phương thức này được gọi mỗi khi timer tick
        // Cập nhật trạng thái game và vẽ lại
        repaint();
    }


    public void stopGame() {
        gameTimer.stop();
    }

    public void resumeGame() {
        gameTimer.start();
    }

}