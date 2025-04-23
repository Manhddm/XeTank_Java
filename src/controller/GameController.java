package controller;

import controller.interfaces.IGameController;
import controller.interfaces.IInputController; // Import IInputController
import core.Sprites;
import model.GameModel;
import model.interfaces.IGameModel;
import model.interfaces.IEntity; // Import IEntity
import model.interfaces.IMovable; // Import IMovable
import view.interfaces.IGameView;
import view.rendering.GamePanel;

import javax.swing.Timer; // Import Timer for game loop
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class GameController implements IGameController, ActionListener { // Implement ActionListener for Timer
    private IGameModel gameModel;
    private IGameView gameView; // Keep the view reference, even if not fully used yet
    private GamePanel gamePanel ;
    private IInputController inputController;
    private Sprites sprites = new Sprites();
    private Timer gameTimer; // Timer for the game loop
    private boolean running = false;
    private final int GAME_TICK_DELAY = 1000 / 120; // Approx 60 FPS

    // Constructor (optional, could do setup in initialize)
    public GameController() {
        // Initialization logic can go here or in initialize()

    }
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void initialize() {
        // Initialize the game timer but don't start it yet
        gameTimer = new Timer(GAME_TICK_DELAY, this);
        System.out.println("GameController initialized.");
    }

    @Override
    public void setModel(IGameModel model) {
        this.gameModel = model;
    }



    // Method to set the input controller
    public void setInputController(IInputController inputController) {
        this.inputController = inputController;
    }

    @Override
    public void startGame() {
        if (gameModel != null && inputController != null && !running) {
            // Ensure model and input are set before starting
            gameModel.initialize(); // Initialize/reset the model if needed
            running = true;
            gameTimer.start();
            System.out.println("Game loop started by GameController.");
        } else {
            System.err.println("Cannot start game: Model, InputController not set or already running.");
        }
    }

    @Override
    public void pauseGame() {
        if (running) {
            gameTimer.stop();
            running = false; // Consider a dedicated 'paused' state if needed
            System.out.println("Game paused by GameController.");
        }
    }

    @Override
    public void resumeGame() {
        if (!running && gameTimer != null) {
            running = true;
            gameTimer.start();
            System.out.println("Game resumed by GameController.");
        }
    }

    @Override
    public void stopGame() {
        if (gameTimer != null) {
            gameTimer.stop();
            running = false;
            System.out.println("Game stopped by GameController.");
            // Potentially show game over screen via view or reset model
            // gameModel.reset();
            // if(gameView != null) gameView.showGameOverScreen(...);
        }
    }

    /**
     * This method is called by the Timer repeatedly.
     * It represents one 'tick' or frame of the game.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        processTick();
        gamePanel.repaint();
    }


    @Override
    public void processTick() {
        if (!running || gameModel == null || inputController == null) {
            return; // Don't process if not running or essential components are missing
        }

        // 1. Process Input
        inputController.processInput(); // Allow input controller to update its state if needed

        // --- Player 1 Input ---
        IEntity player1Entity = gameModel.getPlayer(0); // Assuming index 0 for player 1
        if (player1Entity instanceof IMovable) { // Check if the entity is movable
            IMovable player1 = (IMovable) player1Entity;
            handlePlayerMovement(player1, inputController, 0); // Pass player index 0
            // Handle shooting/actions for player 1
            if (inputController.isPlayerShooting(0)) {
                // TODO: Implement player1.shoot() or similar in Player/GameModel
                System.out.println("Player 1 trying to shoot (implementation needed)");
            }
            if (inputController.isPlayerAction(0)) {
                // TODO: Implement player1 action
                System.out.println("Player 1 trying to perform action (implementation needed)");
            }
        }


        // --- Player 2 Input ---
        IEntity player2Entity = gameModel.getPlayer(1); // Assuming index 1 for player 2
        if (player2Entity instanceof IMovable) { // Check if the entity is movable
            IMovable player2 = (IMovable) player2Entity;
            handlePlayerMovement(player2, inputController, 1); // Pass player index 1
            // Handle shooting/actions for player 2
            if (inputController.isPlayerShooting(1)) {
                // TODO: Implement player2.shoot() or similar in Player/GameModel
                System.out.println("Player 2 trying to shoot (implementation needed)");
            }
            if (inputController.isPlayerAction(1)) {
                // TODO: Implement player2 action
                System.out.println("Player 2 trying to perform action (implementation needed)");
            }
        }

        // 2. Update Game State (including entities)
        gameModel.update();

        // 3. Check Collisions (Would typically call a CollisionController here)
        // collisionController.checkCollisions(gameModel.getAllEntities());

        // 4. Check Game Over Condition
        if (gameModel.isGameOver()) {
            stopGame();
            // if (gameView != null) {
            //     gameView.showGameOverScreen(gameModel.getWinner());
            // }
            System.out.println("Game Over! Winner: " + gameModel.getWinner()); // Placeholder
        }

        // 5. Render (View should handle this, triggered by repaint)
        // The GamePanel's repaint() is usually called separately, often triggered by the Timer
        // If GameView was fully implemented, you might call:
        // if (gameView != null) gameView.render();
    }

    // Helper method to handle movement logic based on input
    private void handlePlayerMovement(IMovable player, IInputController input, int playerIndex) {

        KeyboardController kbController = (KeyboardController) input; // Cast (be careful with this)

        float speed = player.getSpeed() > 0 ? player.getSpeed() : 3.0f; // Default speed if not set
        float dx = 0;
        float dy = 0;


        if (playerIndex == 0) { // Player 1 (WASD)
            if (kbController.isUpP1Pressed()) {
                dy -= speed;
                player.setImage(sprites.player1Up);
            }
            else if (kbController.isDownP1Pressed()) {
                dy += speed;
                player.setImage(sprites.player1Down);
            }
            else  if (kbController.isLeftP1Pressed()) {
                dx -= speed;
                player.setImage(sprites.player1Left);
            }
            else if (kbController.isRightP1Pressed()) {
                dx += speed;
                player.setImage(sprites.player1Right);
            }
        } else if (playerIndex == 1) { // Player 2 (Arrow Keys)
            if (kbController.isUpP2Pressed()) {
                dy -= speed;
                player.setImage(sprites.player2Up);
            }
            else if (kbController.isDownP2Pressed()) {
                dy += speed;
                player.setImage(sprites.player2Down);
            }
            else if (kbController.isLeftP2Pressed()) {
                dx -= speed;
                player.setImage(sprites.player2Left);
            }
            else if (kbController.isRightP2Pressed()) {
                dx += speed;
                player.setImage(sprites.player2Right);
            }
        }

        // Apply movement if there is any change
        if (dx != 0 || dy != 0) {
            player.storePreviousPosition();
            player.setX(player.getX() + dx);
            player.setY(player.getY() + dy);
            System.out.println("Player " + (playerIndex + 1) + " moved to (" + player.getX() + ", " + player.getY() + ")"); // Debug

        }
    }


    @Override
    public boolean isRunning() {
        return running;
    }
    private BufferedImage FacingPlayer(BufferedImage image,IEntity player, int f) {
        if (image == null) {
            return null;
        }
        int with = image.getWidth();
        int height = image.getHeight();
        return null;
    }


}