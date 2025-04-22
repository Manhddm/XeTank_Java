package model.entities;

import model.base.EntityBase;
import model.interfaces.ICollidable;
import model.interfaces.IEntity;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Water extends EntityBase implements ICollidable {

    @Override
    public boolean collidesWith(IEntity other) {
        return this.getHitBox().intersects(other.getHitBox());
    }

    @Override
    public void handleCollision(IEntity other) {
        if (other instanceof Player) {
            Player player = (Player) other;
            player.setSpeed(player.getSpeed() * 0.5f); // Slow down by half
        }
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    protected void initHitBox() {
        this.bounds = new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }

}
