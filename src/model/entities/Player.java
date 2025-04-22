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
    public Player(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = maxHealth;
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
        this.bounds = new Rectangle((int)x,(int)y,10,10);
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public void setX(float x) {

    }

    @Override
    public void setY(float y) {

    }

    @Override
    public Rectangle getHitBox() {
        return bounds;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {

    }
}
