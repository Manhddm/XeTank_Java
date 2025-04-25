package model.entities;

import model.base.MovableEntityBase;

/**
 * Bullet entity - fired by players
 */
public class Bullet extends MovableEntityBase {
    private int isPlayer;
    public Bullet(int x, int y, int isPlayer) {
        this.x = x;
        this.y = y;
        this.isPlayer = isPlayer;
        initHitBox();
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
    public boolean isSolid() {
        return true;
    }
    public int getIsPlayer() {
        return isPlayer;
    }
    public void setIsPlayer(int isPlayer) {
        this.isPlayer = isPlayer;
    }
}
