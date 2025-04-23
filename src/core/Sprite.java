package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Sprite {
    public static final int SIZE = 64;
    private BufferedImage blueTank;
    private BufferedImage redTank;
    private BufferedImage bullet;

    private BufferedImage grassImage;
    private BufferedImage wallImage;
    private BufferedImage waterImage;
    private BufferedImage floorImage;

    public Sprite() {
        blueTank = createDefaultTank(0x3333FF);
        redTank = createDefaultTank(0xFF3333);
        bullet = createDefaultBullet();

        tryLoadImages();
    }

    private void tryLoadImages() {
        try {
            blueTank = ImageIO.read(new File("res/player/BlueTank.png"));
            redTank = ImageIO.read(new File("res/player/redTank.png"));
            bullet = ImageIO.read(new File("res/player/bullet.png"));

            floorImage = ImageIO.read(new File("res/tiles/land1.png"));
            grassImage = ImageIO.read(new File("res/tiles/grass.png"));
            wallImage = ImageIO.read(new File("res/tiles/wall.png"));
            waterImage = ImageIO.read(new File("res/tiles/water.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage createDefaultTank(int color) {
        BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setColor(new java.awt.Color(color));
        g.fillRect(4, 4, SIZE - 8, SIZE - 8);

        g.setColor(new java.awt.Color(0x666666));
        g.fillRect(SIZE - 8, SIZE/2 - 2, 8, 4);
        
        g.dispose();
        return img;
    }

    private BufferedImage createDefaultBullet() {
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setColor(new java.awt.Color(0x000000));
        g.fillOval(0, 0, 5, 5);
        
        g.dispose();
        return img;
    }

    public BufferedImage getTank(Color color) {
        return color == Color.BLUE  ? blueTank : redTank;
    }

    public BufferedImage getBullet() {
        return bullet;
    }

    public BufferedImage getGrass() {
        return grassImage;
    }

    public BufferedImage getWall() {
        return wallImage;
    }

    public BufferedImage getWater() {
        return waterImage;
    }

    public BufferedImage getFloor() {
        return floorImage;
    }
    
}

