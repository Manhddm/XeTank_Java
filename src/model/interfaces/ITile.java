package model.interfaces;

import java.awt.image.BufferedImage;


public interface ITile extends IEntity {

    BufferedImage getImage();

    void setImage(BufferedImage image);

    int getTileType();

    void setTileType(int type);

    boolean affectsMovement();

    float getMovementFactor();
}
