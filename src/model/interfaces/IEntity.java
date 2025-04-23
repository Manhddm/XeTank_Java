package model.interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Base interface for all game entities
 * Defines the core functionality that all entities must implement
 */
public interface IEntity {
    /**
     * Get the entity's X coordinate
     * @return Current X position
     */
    float getX();
    
    /**
     * Get the entity's Y coordinate
     * @return Current Y position
     */
    float getY();
    
    /**
     * Set the entity's X coordinate
     * @param x New X position
     */
    void setX(float x);
    
    /**
     * Set the entity's Y coordinate
     * @param y New Y position
     */
    void setY(float y);
    
    /**
     * Get the collision hitbox for this entity
     * @return Rectangle representing the hitbox
     */
    Rectangle getHitBox();
    
    /**
     * Draw the entity on screen
     * @param g Graphics context for rendering
     */
    void draw(Graphics g);
    void setImage(BufferedImage image);
    BufferedImage getImage();
    /**
     * Update the entity state
     * Called each game update cycle
     */
    void update();
}
