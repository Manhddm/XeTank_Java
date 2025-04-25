package model.entities;

import model.base.EntityBase;
import model.interfaces.ICollidable;
import model.interfaces.IEntity;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Grass extends EntityBase implements ICollidable {


    public Grass(int x, int y, BufferedImage image) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.hitBox = new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight()); 
    }

    @Override
    public boolean collidesWith(IEntity other) {
        return false;
    }

    @Override
    public void handleCollision(IEntity other) {

    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
