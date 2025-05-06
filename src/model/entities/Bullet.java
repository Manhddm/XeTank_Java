package model.entities;

import model.base.MovableEntityBase;

/**
 * Bullet entity - fired by players
 */
public class Bullet extends MovableEntityBase {
    private int isPlayer;
    private int huong;
    public Bullet(int x, int y, int isPlayer, int huong) {
        this.isPlayer = isPlayer;
        this.huong = huong;
        //ViTriXuatHien(x,y,huong);
        this.y = y;
        this.x = x;
        initHitBox();
    }
    @Override
    public void move() {
        if (this.huong == 1){
            this.x -= speed;
        }
        else if (this.huong == 2){
            this.y -= speed;
        }
        else if (this.huong == 3){
            this.x += speed;
        }
        else if (this.huong == 4){
            this.y += speed;
        }
    }

    @Override
    public void undoMove() {

    }

    @Override
    public void storePreviousPosition() {

    }

    @Override
    public boolean isSolid() {
        return true;
    }
    public int getIsPlayer() {
        return isPlayer;
    }
    public void setIsPlayer(int isPlayer) {
        this.isPlayer = isPlayer;
    }
    private void  ViTriXuatHien(int x, int y, int face){
        if (face == 1){//left
            this.x = x - 32;
            this.y =  (float) (int) y /2;
        }
        else if (face == 2){//up
            this.x = (float) x /2;
            this.y = y - 32;
        }
        else if (face == 3){//right
            this.x = x;
            this.y = (float) y /2;
        }
        else if (face == 4){
            this.x = (float) x /2;
            this.y = y;
        }

    }
}
