package controller.interfaces;

import model.GameModel;
import view.interfaces.IGameView;


public interface IGameController {

    void initialize();
    

    void setModel(GameModel model);


    void startGame();
    

    void pauseGame();
    

    void resumeGame();
    

    void stopGame();

    void processTick();
    

    boolean isRunning();
}
