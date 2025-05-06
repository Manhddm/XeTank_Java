package model.entities;
import core.GameConstants;
import model.base.MovableEntityBase;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Player entity representing a tank in the game
 */
public class Player extends MovableEntityBase {
    private int health;
    private final static int maxHealth = 100;
    private int damage;
    private String name;
    public boolean faceRight = false, faceLeft= false, faceUp = false, faceDown = false;
    public Player(String name, float x, float y, float speed, int face ) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.hitBox = new Rectangle((int)x,(int)y, GameConstants.TILE_SIZE,GameConstants.TILE_SIZE);
        this.health = maxHealth;
        this.speed = speed;
        setFace(face);
    }
    public int getFace(){
        if (faceRight){
            return 3;
        }
        else if (faceLeft){
            return 1;
        }
        else if (faceUp){
            return 2;
        }
        return 4;
    }
    public void setFace(int face) {
        switch (face) {
            case 1:
                this.faceLeft = true;
                faceRight =faceUp = faceDown = false;
                break;
            case 2:
                this.faceUp = true;
                faceRight = faceLeft =faceDown=false;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void move() {}


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
    protected void initHitBox() {
        this.hitBox = new Rectangle((int)x,(int)y,GameConstants.TILE_SIZE,GameConstants.TILE_SIZE);
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
        return hitBox != null ? hitBox.getBounds() : new Rectangle((int)x, (int)y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {
        initHitBox();
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
