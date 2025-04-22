package controller;

import controller.interfaces.IInputController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardController implements IInputController, KeyListener {
    private boolean upP1, downP1, leftP1, rightP1, shootP1, actionP1, shiftP1;
    private boolean upP2, downP2, leftP2, rightP2, shootP2, actionP2, shiftP2;
    private int directionP1 = -1;
    private int directionP2 = -1;

    @Override
    public void initialize() {

    }

    @Override
    public void processInput() {

    }

    @Override
    public boolean isKeyPressed(int keyCode) {

        return false;
    }

    @Override
    public int getPlayerMovementDirection(int playerIndex) {
        return 0;
    }

    @Override
    public int getPlayerRotationDirection(int playerIndex) {
        return 0;
    }

    @Override
    public boolean isPlayerShooting(int playerIndex) {
        return false;
    }

    @Override
    public boolean isPlayerAction(int playerIndex) {
        return false;
    }

    @Override
    public boolean isDirectionLocked(int playerIndex) {
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            //player 1
            case KeyEvent.VK_W :upP1 = true; break;
            case KeyEvent.VK_S :downP1 = true; break;
            case KeyEvent.VK_A :leftP1 = true; break;
            case KeyEvent.VK_D :rightP1 = true; break;
            case KeyEvent.VK_J: shootP1 = true; break;
            //player 2
            case KeyEvent.VK_UP :upP2 = true; break;
            case KeyEvent.VK_DOWN :downP2 = true; break;
            case KeyEvent.VK_LEFT :leftP2 = true; break;
            case KeyEvent.VK_RIGHT :rightP2 = true; break;
            case KeyEvent.VK_NUMPAD1: shootP2 = true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W :upP1 = false; break;
            case KeyEvent.VK_S :downP1 = false; break;
            case KeyEvent.VK_A :leftP1 = false; break;
            case KeyEvent.VK_D :rightP1 = false; break;
            case KeyEvent.VK_J: shootP1 = false; break;

            case KeyEvent.VK_UP :upP2 = false; break;
            case KeyEvent.VK_DOWN :downP2 = false; break;
            case KeyEvent.VK_LEFT :leftP2 = false; break;
            case KeyEvent.VK_RIGHT :rightP2 = false; break;
            case KeyEvent.VK_NUMPAD1:shootP2 = false; break;
        }
    }
    private void updateDirection(){

    }
}
