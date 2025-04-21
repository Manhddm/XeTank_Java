package model.base;

import model.interfaces.IEntity;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class EntityBase implements IEntity {
    protected float x;
    protected float y;
    protected BufferedImage image;
    protected Rectangle bounds;
    public EntityBase(float x, float y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
        initHitBox();
    }
    protected abstract void initHitBox() ;
    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public Rectangle getHitBox() {
        return null;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {
    }
}
