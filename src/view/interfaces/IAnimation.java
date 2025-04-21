package view.interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Interface for animations
 */
public interface IAnimation {
    /**
     * Start the animation
     */
    void start();
    
    /**
     * Stop the animation
     */
    void stop();
    
    /**
     * Update the animation state
     */
    void update();
    
    /**
     * Check if animation is currently running
     * @return true if animation is running, false otherwise
     */
    boolean isRunning();
    
    /**
     * Get the current frame of the animation
     * @return Current frame as BufferedImage
     */
    BufferedImage getCurrentFrame();
    
    /**
     * Render the animation at specified coordinates
     * @param g Graphics context
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    void render(Graphics g, int x, int y);
    
    /**
     * Check if animation has completed
     * @return true if animation has completed, false otherwise
     */
    boolean isCompleted();
    
    /**
     * Reset animation to start
     */
    void reset();
}
