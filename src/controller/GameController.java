package controller;


import controller.interfaces.IGameController;
import model.entities.Player;
import model.interfaces.IGameModel;
import view.interfaces.IGameView;


public class GameController implements IGameController {
    private KeyboardController keyboardController;
    private Player player;

    @Override
    public void initialize() {

    }

    @Override
    public void setModel(IGameModel model) {

    }

    @Override
    public void setView(IGameView view) {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void stopGame() {

    }

    @Override
    public void processTick() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
