package model.base;

import model.interfaces.IEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StationaryEntityBase implements IEntity {

    protected float x;

    protected float y;

    protected BufferedImage image;

    protected Rectangle bounds;

}
