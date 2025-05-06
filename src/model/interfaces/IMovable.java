package model.interfaces;

/**
 * Interface for entities that can move
 * Extends IEntity with movement capabilities
 */
public interface IMovable extends IEntity {

    void move();

    boolean getHidden();

    void setHidden(boolean hidden);

    void undoMove();

    float getSpeed();

    void setSpeed(float speed);

    void storePreviousPosition();
}
