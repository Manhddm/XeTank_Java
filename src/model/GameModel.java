package model;

import core.GameConstants;
import model.entities.*;
import model.interfaces.IEntity;
import model.interfaces.IGameModel;
import view.renderers.PlayerRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    //Cho khoi tao
    @Override
    public void initialize() {
        player1 = new Player("Blue",100,100);
        player2 = new Player("Red",500,500);
        BufferedImage image ;
        URL imageURL = GameModel.class.getResource("/res/player/BlueTank.png");
        if (imageURL != null) {
            try {
                image = ImageIO.read(imageURL);
                player1.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageURL = GameModel.class.getResource("/res/player/RedTank.png");
        if (imageURL != null) {
            try {
                image = ImageIO.read(imageURL);
                player2.setImage(image);
            } catch (IOException e) {}
        }
    }

    @Override
    public void update() {

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

    @Override
    public List<? extends IEntity> getAllEntities() {
        List<IEntity> all = new ArrayList<>();
        if (player1 != null) all.addAll((Collection<? extends IEntity>) player1);
        // Thêm các loại entity khác vào list 'all'
        return all;
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

    }
}
