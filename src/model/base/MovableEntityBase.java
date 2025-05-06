package model.base;

import model.interfaces.IMovable;

public abstract class MovableEntityBase extends EntityBase implements IMovable {
    protected float speed;
    protected float prevX;
    protected float prevY;
    protected boolean hidden;

    @Override
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public boolean getHidden() {
        return hidden;
    }

    @Override
    public void move() {

    }

    @Override
    public void undoMove() {
        this.x = this.prevX;
        this.y = this.prevY;
        updateHitBox();
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void storePreviousPosition() {
        this.prevX = this.x;
        this.prevY = this.y;
    }
}
