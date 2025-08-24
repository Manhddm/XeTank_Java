package view.rendering;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(JPanel mainGamePanel) {
        setTitle("Xe Tank Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        add(mainGamePanel);
        
        pack();
        setLocationRelativeTo(null);
    }
}