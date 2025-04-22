package model.entities;

import model.base.EntityBase;
import model.interfaces.ICollidable;
import model.interfaces.IEntity;
import view.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ItemEntity - represents collectible items in the game
 * Could be powerups, health, extra lives, etc.
 */
public class ItemEntity extends EntityBase implements ICollidable {

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
