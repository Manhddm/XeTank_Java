package model.map;

import core.GameConstants;
import core.Sprite;
import model.entities.Grass;
import model.entities.Wall;
import model.entities.Water;
import model.interfaces.IEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


public class GameMap {
    int mapTileNum[][];
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Water> waters = new ArrayList<>();
    ArrayList<Grass> grasses = new ArrayList<>();
    private BufferedImage mapImage;
    private Sprite sprite;
    
    public GameMap() {
        mapTileNum = new int[GameConstants.MAX_SCREEN_COL][GameConstants.MAX_SCREEN_ROW];
        sprite = new Sprite();

        loadMap();
        drawMapToImage();
    }

    public void loadMap() {
        try {
            InputStream is = new FileInputStream("map/Maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            
            while(col < GameConstants.MAX_SCREEN_COL && row < GameConstants.MAX_SCREEN_ROW) {

                String line = br.readLine();
                if(line == null) {
                    break;
                }

                while(col < GameConstants.MAX_SCREEN_COL) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == GameConstants.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawMapToImage() {
        int width = GameConstants.MAX_SCREEN_COL * GameConstants.TILE_SIZE;
        int height = GameConstants.MAX_SCREEN_ROW * GameConstants.TILE_SIZE;
        mapImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mapImage.createGraphics();

        int col = 0, row = 0;
        int x = 0, y = 0;

        while(col < GameConstants.MAX_SCREEN_COL && row < GameConstants.MAX_SCREEN_ROW) {
            
            int tileNum = mapTileNum[col][row];
            if(tileNum == 1) {
                g2.drawImage(sprite.getFloor(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
                g2.drawImage(sprite.getWall(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
                Wall wall = new Wall(x, y, sprite.getWall());
                walls.add(wall);
            } else if(tileNum == 2) {
                g2.drawImage(sprite.getFloor(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
                g2.drawImage(sprite.getWater(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
                Water water = new Water(x, y, sprite.getWater());
                waters.add(water);
            } else if(tileNum == 3) {
                g2.drawImage(sprite.getFloor(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
                g2.drawImage(sprite.getGrass(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
                Grass grass = new Grass(x, y, sprite.getGrass());
                grasses.add(grass);
            } else {
                g2.drawImage(sprite.getFloor(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
            }
            
            col++;
            x += GameConstants.TILE_SIZE;

            if(col == GameConstants.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += GameConstants.TILE_SIZE;
            }
        }
        g2.dispose();
    }

    public void draw(Graphics g) {
        g.drawImage(mapImage, 0, 0, null);
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Water> getWaters() {
        return waters;
    }

    public ArrayList<Grass> getGrasses() {
        return grasses;
    }
}
