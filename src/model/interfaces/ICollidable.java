package model.interfaces;

/**
 * Interface for entities that can collide with others
 */
public interface ICollidable {
    /**
     * Check if this entity collides with another entity
     * @param other The other entity to check collision with
     * @return true if collision occurs, false otherwise
     */
    boolean collidesWith(IEntity other);
    
    /**
     * Handle collision with another entity
     * @param other The entity this object collided with
     */
    void handleCollision(IEntity other);
    
    /**
     * Check if this entity is solid (blocks movement)
     * @return true if solid, false if entities can pass through
     */
    boolean isSolid();
}
