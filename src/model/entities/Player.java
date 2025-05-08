package model.entities;

import core.GameConstants;
import model.base.Direction;
import model.base.MovableEntityBase;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Player entity representing a tank in the game
 */
public class Player extends MovableEntityBase {
    private int lives;
    private final static int maxLives = 100;
    private int damage;
    private Direction direction;
    private int shootCooldown = 20;
    private int currentCooldown = 0;
    private String name;
    public boolean faceRight = false, faceLeft = false, faceUp = false, faceDown = false;

    public Player(String name, float x, float y, float speed, Direction direction) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.hitBox = new Rectangle((int) x, (int) y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        this.lives = maxLives;
        this.speed = speed;
        this.direction = direction;
    }


    public Direction getDirection() {
        return direction;
    }

    public int getFace() {
        if (faceRight) {
            return 3;
        } else if (faceLeft) {
            return 1;
        } else if (faceUp) {
            return 2;
        }
        return 4;
    }

    public void setFace(int face) {
        switch (face) {
            case 1:
                this.faceLeft = true;
                faceRight = faceUp = faceDown = false;
                break;
            case 2:
                this.faceUp = true;
                faceRight = faceLeft = faceDown = false;
                break;
            case 3:
                this.faceRight = true;
                faceLeft = faceUp = faceDown = false;
                break;
            case 4:
                this.faceDown = true;
                faceLeft = faceUp = faceRight = false;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case UP:
                y -= speed;
                this.direction = Direction.UP;
                break;
            case DOWN:
                y += speed;
                this.direction = Direction.DOWN;
                break;
            case LEFT:
                x -= speed;
                this.direction = Direction.LEFT;
                break;
            case RIGHT:
                x += speed;
                this.direction = Direction.RIGHT;
                break;
        }
    }


    @Override
    public void undoMove() {
        super.undoMove();
    }

    @Override
    public float getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(float speed) {
        super.setSpeed(speed);
    }

    @Override
    public void storePreviousPosition() {
        super.storePreviousPosition();
    }


    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public float getY() {
        return super.getY();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox != null ? hitBox.getBounds() : new Rectangle((int) x, (int) y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {
        hitBox.x = (int) this.x;
        hitBox.y = (int) this.y;
        if (currentCooldown>0) currentCooldown--;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    public Bullet shoot() {
        if (currentCooldown <= 0) {
            currentCooldown = shootCooldown;
            int bulletX = (int) this.x;
            int bulletY = (int) this.y;

            switch (this.direction) {
                case UP: 
                    bulletX = (int) this.x + GameConstants.TILE_SIZE / 2 - 3;
                    bulletY = (int) this.y ;
                    System.out.println("dan di len");
                    break;  
                case DOWN: 
                    bulletX = (int) this.x + GameConstants.TILE_SIZE / 2 - 3;
                    bulletY = (int) this.y + GameConstants.TILE_SIZE;
                    System.out.println("dan di xuong");
                    break;
                case RIGHT: 
                    bulletX = (int) this.x + GameConstants.TILE_SIZE;
                    bulletY = (int) this.y + GameConstants.TILE_SIZE / 2 - 3;
                    System.out.println("dan di phai");
                    break;
                case LEFT: 
                    bulletX = (int) this.x - 5;
                    bulletY = (int) this.y + GameConstants.TILE_SIZE / 2 - 3;
                    System.out.println("dan di trai");
                    break;
            }
            return new Bullet(bulletX, bulletY, name.equals("Blue") ? 0 : 1,this.direction);
        }
        return null;
    }
}
