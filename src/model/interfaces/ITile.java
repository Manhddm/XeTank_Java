package model.interfaces;

import java.awt.image.BufferedImage;

/**
 * Interface for map tiles
 */
public interface ITile extends IEntity {
    /**
     * Get the tile's image representation
     * @return BufferedImage of the tile
     */
    BufferedImage getImage();
    
    /**
     * Set the tile's image
     * @param image New image for the tile
     */
    void setImage(BufferedImage image);
    
    /**
     * Get the type ID of this tile
     * @return Tile type identifier
     */
    int getTileType();
    
    /**
     * Set the type ID of this tile
     * @param type New tile type identifier
     */
    void setTileType(int type);
    
    /**
     * Check if this tile affects entity movement (like water slowing down)
     * @return true if movement is affected, false otherwise
     */
    boolean affectsMovement();
    
    /**
     * Get the movement modifier factor for this tile
     * @return Factor to apply to entity speed (1.0 = normal, <1.0 = slower, >1.0 = faster)
     */
    float getMovementFactor();
}
