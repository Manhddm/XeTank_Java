package model.interfaces;

import model.base.Direction;


/**
 * Interface for entities that can move
 * Extends IEntity with movement capabilities
 */
public interface IMovable extends IEntity {

    void move(Direction direction);

    boolean getHidden();

    void setHidden(boolean hidden);

    void undoMove();

    float getSpeed();

    void setSpeed(float speed);

    void storePreviousPosition();
}
