package view.renderers;

import core.GameConstants;
import model.entities.Bullet;
import model.entities.Player;
import model.interfaces.IEntity;
import view.interfaces.IRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * BulletRenderer - responsible for rendering Bullet entities
 */
public class BulletRenderer implements IRenderer {

    @Override
    public void render(Graphics g, IEntity entity) {
        if (!(entity instanceof Bullet bulletToRender)){
            return;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bulletToRender.getImage(),(int)bulletToRender.getX(),(int)bulletToRender.getY(), 5,5,null);
    }


    @Override
    public void renderUI(Graphics g) {

    }

    @Override
    public void dispose() {

    }
}
