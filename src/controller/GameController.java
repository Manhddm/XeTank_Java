package controller;

import controller.interfaces.IGameController;
import controller.interfaces.IInputController; // Import IInputController
import model.interfaces.IGameModel;
import model.interfaces.IEntity; // Import IEntity
import model.interfaces.IMovable; // Import IMovable
import view.interfaces.IGameView;

import javax.swing.Timer; // Import Timer for game loop
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameController implements IGameController, ActionListener { // Implement ActionListener for Timer
    private IGameModel gameModel;
    private IGameView gameView; // Keep the view reference, even if not fully used yet
    private IInputController inputController;
    private Timer gameTimer; // Timer for the game loop
    private boolean running = false;
    private final int GAME_TICK_DELAY = 1000 / 60; // Approx 60 FPS

    // Constructor (optional, could do setup in initialize)
    public GameController() {
        // Initialization logic can go here or in initialize()
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

    @Override
    public void setView(IGameView view) {
        this.gameView = view; // Store the view reference
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
        // Example: Get direction from KeyboardController (assuming it provides simple directions)
        // You'll need to refine KeyboardController.getPlayerMovementDirection
        // or add methods like isMoveUpPressed(playerIndex), isMoveDownPressed(playerIndex) etc.

        // --- Placeholder movement logic ---
        // This needs refinement based on how KeyboardController exposes directions.
        // Let's assume KeyboardController has methods like isUpP1(), isDownP1(), etc.
        // Or better, get a movement vector/direction enum.

        // --- Simplified Example (assuming direct access or helper methods in KeyboardController) ---
        // This part NEEDS to be adapted based on your ACTUAL KeyboardController implementation.
        // For instance, if KeyboardController stores boolean flags like upP1, downP1 etc:

        KeyboardController kbController = (KeyboardController) input; // Cast (be careful with this)

        float speed = player.getSpeed() > 0 ? player.getSpeed() : 2.0f; // Default speed if not set
        float dx = 0;
        float dy = 0;


        if (playerIndex == 0) { // Player 1 (WASD)
            if (kbController.isUpP1Pressed()) dy -= speed;
            if (kbController.isDownP1Pressed()) dy += speed;
            if (kbController.isLeftP1Pressed()) dx -= speed;
            if (kbController.isRightP1Pressed()) dx += speed;
        } else if (playerIndex == 1) { // Player 2 (Arrow Keys)
            if (kbController.isUpP2Pressed()) dy -= speed;
            if (kbController.isDownP2Pressed()) dy += speed;
            if (kbController.isLeftP2Pressed()) dx -= speed;
            if (kbController.isRightP2Pressed()) dx += speed;
        }

        // Apply movement if there is any change
        if (dx != 0 || dy != 0) {
            // Store previous position *before* changing coordinates
            player.storePreviousPosition();

            // Update player position (basic example)
            // More sophisticated movement might involve direction vectors, angles, etc.
            player.setX(player.getX() + dx);
            player.setY(player.getY() + dy);

            // After moving, collision detection should happen (likely in gameModel.update or CollisionController)
            // If a collision occurs, player.undoMove() might be called.
            System.out.println("Player " + (playerIndex + 1) + " moved to (" + player.getX() + ", " + player.getY() + ")"); // Debug
        }
    }


    @Override
    public boolean isRunning() {
        return running;
    }

    // --- Helper methods needed in KeyboardController for the example above ---
    // You would need to add these (or similar logic) to KeyboardController.java
    /*
    public boolean isUpP1Pressed() { return upP1; }
    public boolean isDownP1Pressed() { return downP1; }
    public boolean isLeftP1Pressed() { return leftP1; }
    public boolean isRightP1Pressed() { return rightP1; }
    public boolean isShootP1Pressed() { return shootP1; } // Assuming you add shootP1 flag

    public boolean isUpP2Pressed() { return upP2; }
    public boolean isDownP2Pressed() { return downP2; }
    public boolean isLeftP2Pressed() { return leftP2; }
    public boolean isRightP2Pressed() { return rightP2; }
    public boolean isShootP2Pressed() { return shootP2; } // Assuming you add shootP2 flag
    */

}