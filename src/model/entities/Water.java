package model.entities;

import model.base.EntityBase;
import model.interfaces.ICollidable;
import model.interfaces.IEntity;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Water extends EntityBase implements ICollidable {

    @Override
    protected void initHitBox() {

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
