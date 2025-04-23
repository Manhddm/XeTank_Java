package model;

import core.GameConstants;
import model.entities.*;
import model.interfaces.IEntity;
import model.interfaces.IGameModel;
// import view.renderers.PlayerRenderer; // Không cần import renderer trong model

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File; // Dùng File để đọc ảnh từ đường dẫn cục bộ
import java.io.IOException;
import java.io.InputStream; // Dùng InputStream để đọc ảnh từ resources
import java.net.URL;
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
    List<IEntity> allEntities = new ArrayList<>(); // Khởi tạo danh sách ngay lập tức

    private float defaultPlayerSpeed = 5.0f; // Tốc độ mặc định cho người chơi

    @Override
    public void initialize() {
        System.out.println("Initializing GameModel...");
        allEntities.clear(); // Xóa các thực thể cũ trước khi khởi tạo lại

        // --- Khởi tạo người chơi ---
        player1 = new Player("Blue", 100, 100, defaultPlayerSpeed); // Vị trí và tốc độ ban đầu P1
        player2 = new Player("Red", GameConstants.GAME_SCREEN_WIDTH - 100 - (GameConstants.TILE_SIZE * 2), // Gần góc phải
                GameConstants.GAME_SCREEN_HEIGHT - 100 - (GameConstants.TILE_SIZE * 2), defaultPlayerSpeed); // Vị trí và tốc độ ban đầu P2

        // --- Tải ảnh cho người chơi (Cách tốt hơn: dùng ClassLoader) ---
        // Lưu ý: Đường dẫn tuyệt đối như "D:/..." rất không linh hoạt.
        // Nên đặt ảnh vào thư mục `res` trong project và đọc bằng ClassLoader.
        // Ví dụ cấu trúc: src/..., res/player/BlueTank.png
        BufferedImage p1Image = loadImageFromResources("/res/player/BlueTankRight.png"); // Đường dẫn tương đối từ thư mục resources
        if (p1Image != null) {
            player1.setImage(p1Image);
            System.out.println("Player 1 image loaded.");
        } else {
            System.err.println("Failed to load Player 1 image.");
        }

        BufferedImage p2Image = loadImageFromResources("/res/player/RedTankLeft.png");
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

//        // --- Khởi tạo tường (Ví dụ) ---
//        // Tạo một đường viền tường xung quanh màn hình
//        int wallSize = GameConstants.TILE_SIZE;
//        // Tường trên và dưới
//        for (int col = 0; col < GameConstants.MAX_SCREEN_COL; col++) {
//            addEntity(new Wall(col * wallSize, 0)); // Hàng trên cùng
//            addEntity(new Wall(col * wallSize, GameConstants.GAME_SCREEN_HEIGHT - wallSize)); // Hàng dưới cùng
//        }
//        // Tường trái và phải (bỏ qua góc đã có tường)
//        for (int row = 1; row < GameConstants.MAX_SCREEN_ROW - 1; row++) {
//            addEntity(new Wall(0, row * wallSize)); // Cột trái
//            addEntity(new Wall(GameConstants.GAME_SCREEN_WIDTH - wallSize -10, row * wallSize)); // Cột phải (-10 là do width màn hình dư ra)
//        }
       //System.out.println("Walls added to entities list.");

        System.out.println("GameModel initialized with " + allEntities.size() + " entities.");
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
        // Tạo bản sao để tránh ConcurrentModificationException khi thêm/xóa thực thể trong lúc lặp
        List<IEntity> entitiesToUpdate = new ArrayList<>(allEntities);
        for (IEntity entity : entitiesToUpdate) {
            entity.update(); // Gọi update cho từng thực thể (hiện tại chủ yếu là Player)
        }
        // TODO: Thêm logic cập nhật khác nếu cần (ví dụ: tạo kẻ địch, item,...)
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
    private <T extends IEntity> void addEntity(T entity) {
        if (entity != null) {
            allEntities.add(entity);
        }
    }

    private <T extends IEntity> void removeEntity(T entity) {
        if (entity != null) {
            allEntities.remove(entity);
        }
    }

    @Override
    public List<? extends IEntity> getAllEntities() {
        // Trả về một bản sao không thể thay đổi hoặc một bản sao mới để bảo vệ danh sách gốc
        return allEntities;
        // Hoặc return Collections.unmodifiableList(allEntities);
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
