package view.interfaces;

import model.interfaces.IEntity;

import java.awt.*;

/**
 * Interface for rendering components
 */
public interface IRenderer {
    /**
     * Render a specific entity
     * @param g Graphics context
     * @param entity Entity to render
     */
    void render(Graphics g, IEntity entity);

    /**
     * Render UI elements (health bars, scores, etc.)
     * @param g Graphics context
     */
    void renderUI(Graphics g);
    
    /**
     * Clean up resources when renderer is no longer needed
     */
    void dispose();
}
