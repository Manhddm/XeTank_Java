package view;

import view.interfaces.IAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Animation - handles frame-by-frame animations
 */
public class Animation implements IAnimation {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public BufferedImage getCurrentFrame() {
        return null;
    }

    @Override
    public void render(Graphics g, int x, int y) {

    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public void reset() {

    }
}
