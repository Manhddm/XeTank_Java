package model.interfaces;

/**
 * Interface for entities that can move
 * Extends IEntity with movement capabilities
 */
public interface IMovable extends IEntity {
    /**
     * Move the entity based on its current state and direction
     */
    void move();
    
    /**
     * Revert the entity to its previous position (used after collision detection)
     */
    void undoMove();
    
    /**
     * Get the entity's movement speed
     * @return Current speed value
     */
    float getSpeed();
    
    /**
     * Set the entity's movement speed
     * @param speed New speed value
     */
    void setSpeed(float speed);
    
    /**
     * Store the current position for possible rollback
     */
    void storePreviousPosition();
}
