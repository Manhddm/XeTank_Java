package model.entities;
import model.base.MovableEntityBase;


import java.awt.*;

/**
 * Player entity representing a tank in the game
 */
public class Player extends MovableEntityBase {
    private int health;
    private final static int maxHealth = 100;
    private int damage;
    private String name;

    public Player(String name, float x, float y, float speed ) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = maxHealth;
        this.speed = speed;
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
    public void move() {
    }

    @Override
    public void undoMove() {
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(float speed) {

    }

    @Override
    public void storePreviousPosition() {

    }

    @Override
    protected void initHitBox() {
        this.hitBox = new Rectangle((int)x,(int)y,64,64);
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
        return hitBox != null ? hitBox.getBounds() : new Rectangle((int)x, (int)y, 64, 64);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {

    }
}
