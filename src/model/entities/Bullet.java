package model.entities;

import core.GameConstants;
import model.GameModel;
import model.base.Direction;
import model.base.MovableEntityBase;

import java.awt.*;

/**
 * Bullet entity - fired by players
 */
public class Bullet extends MovableEntityBase {
    private int isPlayer;
    private Direction direction;
    private int damage;
    private boolean dead;
    public Bullet(int x, int y, int isPlayer, Direction direction) {
        this.isPlayer = isPlayer;
        dead = false;
        //ViTriXuatHien(x,y,huong);
        this.y = y;
        this.x = x;
        this.direction = direction;
        this.damage = 20;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void undoMove() {

    }

    @Override
    public void update() {
        if (this.x < 0 || this.x > GameConstants.GAME_SCREEN_WIDTH || this.y < 0 || this.y > GameConstants.GAME_SCREEN_HEIGHT) {
            dead = true;
        }
        hitBox.x = (int) this.x;
        hitBox.y = (int) this.y;
    }
    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
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
