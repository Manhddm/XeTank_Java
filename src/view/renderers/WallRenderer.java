package view.renderers;

import model.entities.Wall;
import model.interfaces.IEntity;
import view.interfaces.IRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * WallRenderer - responsible for rendering Wall entities
 */
public class WallRenderer implements IRenderer {

    @Override
    public void render(Graphics g, IEntity entity) {
        if (entity instanceof Wall wall) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(wall.getImage(),(int)wall.getX(),(int)wall.getY(),32,32,null);
        }
    }


    @Override
    public void renderUI(Graphics g) {

    }

    @Override
    public void dispose() {

    }
}
