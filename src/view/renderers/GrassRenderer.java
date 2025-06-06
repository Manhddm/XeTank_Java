package view.renderers;

import model.entities.Grass;
import model.interfaces.IEntity;
import view.interfaces.IRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * GrassRenderer - responsible for rendering Grass entities
 */
public class GrassRenderer implements IRenderer {

    @Override
    public void render(Graphics g, IEntity entity) {
        if (entity instanceof Grass grass) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(grass.getImage(),(int)grass.getX(),(int)grass.getY(),32,32,null);
        }
    }



    @Override
    public void renderUI(Graphics g) {

    }

    @Override
    public void dispose() {

    }
}
