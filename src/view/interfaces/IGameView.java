package view.interfaces;

import model.interfaces.IGameModel;

import java.awt.*;

/**
 * Main game view interface
 * Responsible for all rendering
 */
public interface IGameView {
    /**
     * Initialize the game view
     */
    void initialize();
    
    /**
     * Set the game model to render
     * @param model Game model to render
     */
    void setModel(IGameModel model);
    
    /**
     * Render the entire game state
     * @param g Graphics context
     */
    void render(Graphics g);
    
    /**
     * Show game over screen
     * @param winnerIndex Index of the winning player
     */
    void showGameOverScreen(int winnerIndex);
    
    /**
     * Show main menu
     */
    void showMainMenu();
    
    /**
     * Clean up resources when view is no longer needed
     */
    void dispose();
}
