package model.entities;

import model.base.Direction;
import model.base.MovableEntityBase;

import java.awt.*;

/**
 * Bullet entity - fired by players
 */
public class Bullet extends MovableEntityBase {
    private int isPlayer;
    private Direction direction;
    public Bullet(int x, int y, int isPlayer, Direction direction) {
        this.isPlayer = isPlayer;
        //ViTriXuatHien(x,y,huong);
        this.y = y;
        this.x = x;
        this.direction = direction;
        //initHitBox();
        this.hitBox = new Rectangle(x, y, 5, 5);
    }
    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }

    @Override
    public void undoMove() {

    }

    @Override
    public void storePreviousPosition() {

    }

    @Override
    public boolean isSolid() {
        return true;
    }

    public int getIsPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(int isPlayer) {
        this.isPlayer = isPlayer;
    }

}
