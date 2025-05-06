package model.entities;

import core.GameConstants;
import model.base.EntityBase;
import model.interfaces.ICollidable;
import model.interfaces.IEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Wall entity - blocks player movement and bullets
 */
public class Wall extends EntityBase implements ICollidable {

    public Wall(int x, int y, BufferedImage image) {
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
        return true;
    }

    // @Override
    // protected void initHitBox() {
    //     this.hitBox = new Rectangle((int)x,(int)y, GameConstants.TILE_SIZE-5,GameConstants.TILE_SIZE-5);
    // }
}
