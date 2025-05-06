package model;

import core.GameConstants;
import core.Sprites;
import model.entities.*;
import model.interfaces.IEntity;
import model.interfaces.IGameModel;
import model.map.GameMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream; // Dùng InputStream để đọc ảnh từ resources

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
    List<IEntity> allEntities = new ArrayList<>(); // Khởi tạo danh sách ngay lập tức
    GameMap gameMap =  new GameMap();
    // public final static float defaultPlayerSpeed = 5.0f; // Tốc độ mặc định cho người chơi

    @Override
    public void initialize() {
        System.out.println("Initializing GameModel...");
        allEntities.clear(); // Xóa các thực thể cũ trước khi khởi tạo lại

        // --- Khởi tạo người chơi ---
        player1 = new Player("Blue", 40, 40, GameConstants.DEAFAULT_PLAYER_SPEED,3); // Vị trí và tốc độ ban đầu P1
        player2 = new Player("Red", GameConstants.GAME_SCREEN_WIDTH - 100 - (GameConstants.TILE_SIZE * 2), // Gần góc phải
                GameConstants.GAME_SCREEN_HEIGHT - 100 - (GameConstants.TILE_SIZE * 2), GameConstants.DEAFAULT_PLAYER_SPEED, 1); // Vị trí và tốc độ ban đầu P2


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

    // Helper method để tải ảnh từ thư mục resources
    private BufferedImage loadImageFromResources(String path) {
        try {
            // Lấy InputStream từ đường dẫn resource
            InputStream stream = getClass().getResourceAsStream(path);
            if (stream == null) {
                System.err.println("Cannot find resource: " + path);
                // Thử đọc từ File như một phương án dự phòng (ít khuyến khích)
                // File file = new File("res" + path); // Giả sử thư mục res ngang hàng với src
                // if (file.exists()) {
                //     return ImageIO.read(file);
                // }
                return null;
            }
            // Đọc ảnh từ InputStream
            BufferedImage image = ImageIO.read(stream);
            stream.close(); // Đóng stream sau khi đọc
            return image;
        } catch (IOException e) {
            System.err.println("Error loading image from resources: " + path);
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void update() {
        // Tạo danh sách các đạn cần xóa sau khi duyệt qua
    }

    @Override
    public IEntity getPlayer(int playerIndex) {
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
            allEntities.add(entity);
        }
    }

    public <T extends IEntity> void removeEntity(T entity) {
        if (entity != null) {
            allEntities.remove(entity);
        }
    }

    @Override
    public List<? extends IEntity> getAllEntities() {

        return allEntities;
    }

    @Override
    public <T extends IEntity> List<T> getEntitiesOfType(Class<T> entityClass) {
        // Lọc danh sách allEntities để trả về các thực thể thuộc lớp được yêu cầu
        return allEntities.stream()
                .filter(entityClass::isInstance)
                .map(entityClass::cast)
                .collect(Collectors.toList());
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
