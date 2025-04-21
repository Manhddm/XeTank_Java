package controller.interfaces;

import model.interfaces.IGameModel;
import view.interfaces.IGameView;


public interface IGameController {

    void initialize();
    

    void setModel(IGameModel model);
    

    void setView(IGameView view);
    

    void startGame();
    

    void pauseGame();
    

    void resumeGame();
    

    void stopGame();

    void processTick();
    

    boolean isRunning();
}
