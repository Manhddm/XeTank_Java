package model.base;

import model.interfaces.IMovable;

public abstract class MovableEntityBase extends EntityBase implements IMovable {
    protected float speed;

    @Override
    public void undoMove() {}

    @Override
    public void move() {}

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
