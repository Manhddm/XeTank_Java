package model.interfaces;

import java.util.List;

/**
 * Main game model interface
 * Manages all game state and entities
 */
public interface IGameModel {
    /**
     * Initialize the game model
     */
    void initialize();
    
    /**
     * Update all game entities and state
     */
    void update();
    
    /**
     * Get the player entity
     * @param playerIndex Player index (0 for first player, 1 for second player)
     * @return The player entity
     */
    IEntity getPlayer(int playerIndex);
    
    /**
     * Get all entities in the game
     * @return List of all entities
     */
    List<? extends IEntity> getStaticEntities();
    
    /**
     * Get all entities of a specific type
     * @param <T> Entity type
     * @param entityClass Class of the entity type
     * @return List of entities of the specified type
     */
    <T extends IEntity> List<T> getStaticEntitiesOfType(Class<T> entityClass);
    
    /**
     * Check if the game is over
     * @return true if game is over, false otherwise
     */
    boolean isGameOver();
    
    /**
     * Get the winner index
     * @return Player index that won, or -1 if no winner yet
     */
    int getWinner();
    
    /**
     * Reset the game to initial state
     */
    void reset();
}
