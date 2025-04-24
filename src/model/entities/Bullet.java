package model.entities;

import model.base.MovableEntityBase;

/**
 * Bullet entity - fired by players
 */
public class Bullet extends MovableEntityBase {

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
    public boolean isSolid() {
        return true;
    }
}
