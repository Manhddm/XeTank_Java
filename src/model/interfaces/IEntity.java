package model.interfaces;

import java.awt.*;

/**
 * Base interface for all game entities
 * Defines the core functionality that all entities must implement
 */
public interface IEntity {

    float getX();

    float getY();

    void setX(float x);

    void setY(float y);

    Rectangle getHitBox();
    
    void draw(Graphics g);

    void update();
}
