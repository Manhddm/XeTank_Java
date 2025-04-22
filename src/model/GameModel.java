package model;

import core.GameConstants;
import model.entities.*;
import model.interfaces.IEntity;
import model.interfaces.IGameModel;
import view.renderers.PlayerRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    List<IEntity> allEntities = new ArrayList<>() ;
    //Cho khoi tao
    @Override
    public void initialize() {

        player1 = new Player("Blue",100,100);
        player2 = new Player("Red",500,500);
        BufferedImage image ;

        //System.out.println(imageURL);
        //if (imageURL != null) {
            try {
                image = ImageIO.read(new File("D:/java/XeTank_Java/res/player/BlueTank.png"));
                player1.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
//        else {
//            System.out.println("Khong doc duoc anh");
//        }
//        imageURL = GameModel.class.getResource("XeTank_Java/res/player/RedTank.png");
//        if (imageURL != null) {
            try {
                image = ImageIO.read(new File("D:/java/XeTank_Java/res/player/RedTank.png"));
                player2.setImage(image);
            } catch (IOException e) {}
//        }
        addEntity(player1);
        addEntity(player2);
    }

    @Override
    public void update() {
        List<IEntity> entitiesToUpdate = new ArrayList<>(allEntities); // Tạo bản sao để tránh ConcurrentModificationException
        for (IEntity entity : entitiesToUpdate) {
            entity.update();
        }
    }

    @Override
    public IEntity getPlayer(int playerIndex) {
        if (playerIndex == 1){
            return player1;
        }
        else{
            return player2;
        }
    }
    private <T extends IEntity> void addEntity(T entity) {
        allEntities.add(entity);
    }
    private <T extends IEntity> void removeEntity(T entity) {
        allEntities.remove(entity);
    }
    @Override
    public List<? extends IEntity> getAllEntities() {

        return new ArrayList<>(allEntities);
    }

    @Override
    public <T extends IEntity> List<T> getEntitiesOfType(Class<T> entityClass) {
        return List.of();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getWinner() {
        return 0;
    }

    @Override
    public void reset() {
        allEntities.clear();
        initialize();
    }
}
