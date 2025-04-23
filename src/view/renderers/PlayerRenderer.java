package view.renderers;
import model.entities.Player;
import model.interfaces.IEntity;
import view.interfaces.IRenderer;
import java.awt.*;


/**
 * PlayerRenderer - responsible for rendering Player entities
 */
public class PlayerRenderer implements IRenderer {
    public PlayerRenderer() {  }

    @Override
    public void render(Graphics g, IEntity entity) {
        if (!(entity instanceof Player)){
            return;
        }
        Player playerToRender = (Player) entity;
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerToRender.getImage(),(int)playerToRender.getX(),(int)playerToRender.getY(),64,64,null);
    }


    @Override
    public void renderUI(Graphics g) {

    }

    @Override
    public void dispose() {

    }
}
