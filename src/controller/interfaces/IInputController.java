package controller.interfaces;


public interface IInputController {

    void initialize();
    
    void processInput();

    boolean isKeyPressed(int keyCode);

    int getPlayerMovementDirection(int playerIndex);
    

    int getPlayerRotationDirection(int playerIndex);
    

    boolean isPlayerShooting(int playerIndex);
    

    boolean isPlayerAction(int playerIndex);

    boolean isDirectionLocked(int playerIndex);
}
