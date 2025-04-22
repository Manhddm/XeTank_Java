package model.base;

import model.interfaces.IEntity;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class EntityBase implements IEntity {
    protected float x;
    protected float y;
    protected BufferedImage image;
    protected Rectangle bounds;
    protected abstract void initHitBox();
    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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
        return bounds.getBounds();
    }

    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, (int) x, (int) y, null);
        }
    }

    @Override
    public void update() {
    }
}
