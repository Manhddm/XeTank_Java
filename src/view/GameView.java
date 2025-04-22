package view;

import model.entities.*;
import model.interfaces.IEntity;
import model.interfaces.IGameModel;
import view.interfaces.IGameView;
import view.interfaces.IRenderer;
import view.renderers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Main game view implementation
 * Responsible for rendering the game state
 */
public class GameView extends JPanel implements IGameView {

    @Override
    public void initialize() {

    }

    @Override
    public void setModel(IGameModel model) {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void showGameOverScreen(int winnerIndex) {

    }

    @Override
    public void showMainMenu() {

    }

    @Override
    public void dispose() {

    }
}
