package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Sprites {
    public BufferedImage player1Up, player1Down, player1Left, player1Right;
    public BufferedImage player2Up, player2Down, player2Left, player2Right;
    public BufferedImage bullet;
    public BufferedImage grass, wall, water,floor;

    public Sprites(){
        player1Up = loadImageFromResources("/res/player/BlueTankUp.png");
        player1Down = loadImageFromResources("/res/player/BlueTankDown.png");
        player1Left = loadImageFromResources("/res/player/BlueTankLeft.png");
        player1Right = loadImageFromResources("/res/player/BlueTankRight.png");

        player2Up = loadImageFromResources("/res/player/RedTankUp.png");
        player2Down = loadImageFromResources("/res/player/RedTankDown.png");
        player2Left = loadImageFromResources("/res/player/RedTankLeft.png");
        player2Right = loadImageFromResources("/res/player/RedTankRight.png");

        bullet = loadImageFromResources("/res/player/bullet.png");
        grass = loadImageFromResources("/res/tiles/grass.png");
        wall = loadImageFromResources("/res/tiles/wall.png");
        water = loadImageFromResources("/res/tiles/water.png");
        floor = loadImageFromResources("/res/tiles/floor.png");

    }
    private BufferedImage loadImageFromResources(String path) {
        try {
            // Lấy InputStream từ đường dẫn resource
            InputStream stream = getClass().getResourceAsStream(path);
            if (stream == null) {
                System.err.println("Cannot find resource: " + path);
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
}
