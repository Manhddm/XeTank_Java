package controller;

import controller.interfaces.IInputController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardController implements IInputController, KeyListener {
    private boolean upP1, downP1, leftP1, rightP1, shootP1, actionP1, shiftP1;
    private boolean upP2, downP2, leftP2, rightP2, shootP2, actionP2, shiftP2;
    private int directionXP1 = -1;
    private int directionYP1 = -1;
    private int directionXP2 = -1;
    private int directionYP2 = -1;

    public int getDirectionXP1() {
        return directionXP1;
    }

    public int getDirectionYP1() {
        return directionYP1;
    }

    public int getDirectionXP2() {
        return directionXP2;
    }

    public int getDirectionYP2() {
        return directionYP2;
    }


    @Override
    public void initialize() {
        upP1 = false;
        downP1 = false;
        leftP1 = false;
        rightP1 = false;
        shiftP1 = false;
        shootP1 = false;
        upP2 = false;
        downP2 = false;
        leftP2 = false;
        rightP2 = false;
        shootP2 = false;
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
    public boolean isPlayerShooting(int playerIndex) {
        if (playerIndex == 0) {
            return shootP1;
        }
        return shootP2;
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
            case KeyEvent.VK_J: shootP1 = false;
                System.out.println("het ban roi"); break;

            case KeyEvent.VK_UP :upP2 = false; break;
            case KeyEvent.VK_DOWN :downP2 = false; break;
            case KeyEvent.VK_LEFT :leftP2 = false; break;
            case KeyEvent.VK_RIGHT :rightP2 = false; break;
            case KeyEvent.VK_NUMPAD1:shootP2 = false; break;
        }
    }

    public void setShootP2(boolean shootP2) {
        this.shootP2 = shootP2;
    }

    public void setShootP1(boolean shootP1) {
        this.shootP1 = shootP1;
    }

    public boolean isUpP1Pressed() { return upP1; }
    public boolean isDownP1Pressed() { return downP1; }
    public boolean isLeftP1Pressed() { return leftP1; }
    public boolean isRightP1Pressed() { return rightP1; }
    public boolean isShootP1Pressed() { return shootP1; } // Assuming you add shootP1 flag

    public boolean isUpP2Pressed() { return upP2; }
    public boolean isDownP2Pressed() { return downP2; }
    public boolean isLeftP2Pressed() { return leftP2; }
    public boolean isRightP2Pressed() { return rightP2; }
    public boolean isShootP2Pressed() { return shootP2; }
}
