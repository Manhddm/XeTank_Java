package view.rendering;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import model.GameModel;
import model.entities.Bullet;
import model.entities.Player;
import model.interfaces.IEntity;
import core.GameConstants;
import model.interfaces.IGameModel;
import model.map.GameMap;
import view.interfaces.IGameView;
import view.renderers.BulletRenderer;
import view.renderers.PlayerRenderer;

public class GamePanel extends JPanel implements IGameView {
    private GameModel gameModel;
    private final GameMap gameMap = new GameMap();
    private PlayerRenderer playerRenderer;
    private BulletRenderer bulletRenderer;
    
    public GamePanel(GameModel gameModel) {
        this.gameModel = gameModel;

        // Khởi tạo các renderer
        this.playerRenderer = new PlayerRenderer();
        this.bulletRenderer = new BulletRenderer();

        setPreferredSize(new Dimension(GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setDoubleBuffered(true);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        gameMap.draw(g2d);
        Font font = new Font("Arial", Font.BOLD, 20);
        if (gameModel == null) {
            g2d.setColor(Color.RED);
            g2d.setFont(font);
            g2d.drawString("Lỗi: GameModel chưa được thiết lập!", 50, 50);
            g2d.dispose();
            return;
        }

        List<? extends IEntity> currentEntities = gameModel.getStaticEntities();

        if (currentEntities != null) {
            List<Player> players = new ArrayList<>(); 
            players.add(gameModel.getPlayer(0)); 
            players.add(gameModel.getPlayer(1));
            List<Bullet> bullets = gameModel.getBullets();
            List<IEntity> playerBullets = new ArrayList<>();
            playerBullets.addAll(players);
            playerBullets.addAll(bullets);
            
            for (IEntity entity : playerBullets) {
                if (entity == null) continue;

                if (entity instanceof Player) {
                    if (playerRenderer != null) {
                        playerRenderer.render(g2d, entity);
                    }
                }
                else if (entity instanceof Bullet) {
                    bulletRenderer.render(g2d, entity);
                }
                else {
                    entity.draw(g2d);
                }
            }
        } else {
            g2d.setColor(Color.YELLOW);
            g2d.drawString("Danh sách thực thể là null trong GameModel!", 100, 120);
        }
        
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString("Player1: " + gameModel.getPlayer(0).getLives() + "/" + gameModel.getPlayer(0).getMaxLives(), 10, 20);
        g2d.drawString("Player2: " + gameModel.getPlayer(1).getLives() + "/" + gameModel.getPlayer(1).getMaxLives(), GameConstants.GAME_SCREEN_WIDTH - 32*5, 20);
        g2d.dispose();
    }
    
    @Override
    public void initialize() {
        // Implementation not needed at this time
    }

    @Override
    public void setModel(IGameModel model) {
        if (model instanceof GameModel) {
            this.gameModel = (GameModel) model;
        }
    }

    @Override
    public void render(Graphics g) {
        // Already handled by paintComponent
    }

    @Override
    public void showGameOverScreen(int winnerIndex) {
        // Implementation for game over screen can be added later
    }

    @Override
    public void showMainMenu() {
        // Implementation for main menu can be added later
    }

    @Override
    public void dispose() {
        // Cleanup resources if needed
    }
}
