package model;

import core.GameConstants;
import core.Sprites;
import model.base.Direction;
import model.entities.*;
import model.interfaces.IEntity;
import model.interfaces.IGameModel;
import model.interfaces.IMovable;
import model.map.GameMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream; 

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main game model implementation
 * Manages all game state and entities
 */
public class GameModel implements IGameModel {
    Player player1;
    Player player2;
    Sprites sprites = new Sprites();
    List<IEntity> staticEntities = new ArrayList<>(); // Khởi tạo danh sách ngay lập tức
    GameMap gameMap =  new GameMap();
    List<Bullet> bullets = new ArrayList<>();

    @Override
    public void initialize() {
        System.out.println("Initializing GameModel...");
        staticEntities.clear(); // Xóa các thực thể cũ trước khi khởi tạo lại

        // --- Khởi tạo người chơi ---
        player1 = new Player("Blue", 40, 40, GameConstants.DEAFAULT_PLAYER_SPEED,Direction.RIGHT); // Vị trí và tốc độ ban đầu P1
        player2 = new Player("Red", GameConstants.GAME_SCREEN_WIDTH - 100 - (GameConstants.TILE_SIZE * 2), // Gần góc phải
                GameConstants.GAME_SCREEN_HEIGHT - 100 - (GameConstants.TILE_SIZE * 2), GameConstants.DEAFAULT_PLAYER_SPEED, Direction.LEFT); // Vị trí và tốc độ ban đầu P2


        BufferedImage p1Image = sprites.player1Right; // Đường dẫn tương đối từ thư mục resources
        if (p1Image != null) {
            player1.setImage(p1Image);
            System.out.println("Player 1 image loaded.");
        } else {
            System.err.println("Failed to load Player 1 image.");
        }

        BufferedImage p2Image = sprites.player2Left;
        if (p2Image != null) {
            player2.setImage(p2Image);
            System.out.println("Player 2 image loaded.");
        } else {
            System.err.println("Failed to load Player 2 image.");
        }

        // Thêm người chơi vào danh sách thực thể

        addEntity(player1);
        addEntity(player2);
        System.out.println("Players added to entities list.");
        ArrayList<Wall> walls = gameMap.getWalls();
        for (Wall wall : walls) {
            addEntity(wall);
        }
        System.out.println("Walls added to entities list.");
        ArrayList<Water> waters = gameMap.getWaters();
        for (Water water : waters) {
            addEntity(water);
        }
        System.out.println("Waters added to entities list.");
        ArrayList<Grass> grasses = gameMap.getGrasses();
        for (Grass grass : grasses) {
            addEntity(grass);
        }
        System.out.println("Grasses added to entities list.");
    }
    

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }
    @Override
    public void update() {
        // Tạo danh sách các đạn cần xóa sau khi duyệt qua
        for (Bullet bullet : bullets) {
            if (bullet.getIsPlayer() == 0){
                bullet.move();
            }
            if (bullet.getIsPlayer() == 1){
                bullet.move();
            }
        }
    }

    @Override
    public Player getPlayer(int playerIndex) {
        if (playerIndex == 0) {
            return player1;
        } else if (playerIndex == 1) {
            return player2;
        }
        return null; // Trả về null nếu index không hợp lệ
    }

    // Sử dụng kiểu generic để đảm bảo an toàn kiểu
    public  <T extends IEntity> void addEntity(T entity) {
        if (entity != null) {
            staticEntities.add(entity);
        }
    }

    public <T extends IEntity> void removeEntity(T entity) {
        if (entity != null) {
            staticEntities.remove(entity);
        }
    }
    @Override
    public List<? extends IEntity> getStaticEntities() {
        return staticEntities;
    }

    @Override
    public <T extends IEntity> List<T> getStaticEntitiesOfType(Class<T> entityClass) {
        // Lọc danh sách staticEntities để trả về các thực thể thuộc lớp được yêu cầu
        return staticEntities.stream()
                .filter(entityClass::isInstance)
                .map(entityClass::cast)
                .collect(Collectors.toList());
    }
    public List<Bullet> getBullets() {
        return bullets;
    }
    @Override
    public boolean isGameOver() {
        // TODO: Implement game over condition (ví dụ: kiểm tra máu người chơi)
        return false; // Tạm thời chưa có game over
    }

    @Override
    public int getWinner() {
        // TODO: Implement logic xác định người chiến thắng
        return -1; // -1 nghĩa là chưa có người thắng
    }

    @Override
    public void reset() {
        // Đơn giản là gọi lại initialize để bắt đầu lại từ đầu
        initialize();
    }
}
