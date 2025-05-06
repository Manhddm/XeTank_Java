package controller;

import controller.interfaces.IGameController;
import controller.interfaces.IInputController; // Import IInputController
import core.GameConstants;
import core.Sprites;
import model.GameModel;
import model.entities.*;
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
import java.util.ArrayList;
import java.util.List;


public class GameController implements IGameController, ActionListener { // Implement ActionListener for Timer
    private GameModel gameModel;
    private CollisionController collisionController;
    private GamePanel gamePanel ;
    private KeyboardController inputController;
    private Sprites sprites = new Sprites();
    private Timer gameTimer; // Timer for the game loop
    private boolean running = false;
    private final int GAME_TICK_DELAY = 1000 / 80; // Approx 60 FPS
    private List<Wall> walls;
    private List<Water> waters ;
    private List<Grass> grasses;

    // Constructor (optional, could do setup in initialize)
    public GameController() {
        // Initialization logic can go here or in initialize()
        collisionController = new CollisionController();
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
    public void setModel(GameModel model) {
        this.gameModel = model;
        this.walls = gameModel.getEntitiesOfType(Wall.class);
        this.waters = gameModel.getEntitiesOfType(Water.class);
        this.grasses = gameModel.getEntitiesOfType(Grass.class);
    }



    // Method to set the input controller
    public void setInputController(KeyboardController inputController) {
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
            Player player1 = (Player) player1Entity;
            handlePlayerMovement(player1, 0); // Pass player index 0
            handlePlayerCollision(player1);
            // Handle shooting/actions for player 1
            if (inputController.isPlayerShooting(0)) {
                handelPlayerShooting(player1);
            }
            if (inputController.isPlayerAction(0)) {
                // TODO: Implement player1 action
                System.out.println("Player 1 trying to perform action (implementation needed)");
            }
        }


        // --- Player 2 Input ---
        IEntity player2Entity = gameModel.getPlayer(1); // Assuming index 1 for player 2
        if (player2Entity instanceof IMovable) { // Check if the entity is movable
            Player player2 = (Player) player2Entity;
            handlePlayerMovement(player2, 1); // Pass player index 1
            handlePlayerCollision(player2);
            // Handle shooting/actions for player 2
            if (inputController.isPlayerShooting(1)) {
                handelPlayerShooting(player2);
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
    void handelPlayerShooting(Player player) {
        int playerIndex =   player == gameModel.getPlayer(0)?0:1;

        if (inputController.isPlayerShooting(playerIndex) && player.canShoot()) {
            // Tính toán vị trí chính xác cho đạn dựa vào hướng của xe tăng
            float bulletX = player.getX();
            float bulletY = player.getY();
            int face = player.getFace();

            // Điều chỉnh vị trí xuất hiện của đạn theo hướng
            switch (face) {
                case 1: // LEFT
                    bulletX = player.getX() - 5;
                    bulletY = player.getY() + GameConstants.TILE_SIZE / 2 - 3;
                    break;
                case 2: // UP
                    bulletX = player.getX() + GameConstants.TILE_SIZE / 2 - 3;
                    bulletY = player.getY() - 5;
                    break;
                case 3: // RIGHT
                    bulletX = player.getX() + GameConstants.TILE_SIZE;
                    bulletY = player.getY() + GameConstants.TILE_SIZE / 2 - 3;
                    break;
                case 4: // DOWN
                    bulletX = player.getX() + GameConstants.TILE_SIZE / 2 - 3;
                    bulletY = player.getY() + GameConstants.TILE_SIZE;
                    break;
            }

            Bullet bullet = new Bullet((int) bulletX, (int) bulletY, playerIndex, face);
            bullet.setImage(sprites.bullet);
            bullet.setSpeed(7f);
            gameModel.addEntity(bullet);

            player.updateLastShotTime();

            // Reset trạng thái bắn ngay lập tức nếu muốn (hoặc giữ lại để kiểm tra nút giữ)
            if (playerIndex == 0) {
                inputController.setShootP1(false);
            } else {
                inputController.setShootP2(false);
            }

            System.out.println("Player " + (playerIndex + 1) + " shot a bullet");
        }
    }
    //Check Collision
    void handlePlayerCollision(IEntity player) {
        IMovable playerX = (IMovable) player;
        //Kiem tra va cham voi tuong khi di chuyen
        //List<Wall> walls = gameModel.getEntitiesOfType(Wall.class);
        if (collisionController.checkCollisionWithStatic(playerX,this.walls)){
            playerX.undoMove();
        }
        //Kiem Tra va cham voi nuoc
        // List<Water> waters = gameModel.getEntitiesOfType(Water.class);
        if (collisionController.checkCollisionWithStatic(playerX,this.waters)){
            playerX.setSpeed(GameConstants.DEAFAULT_PLAYER_SPEED - GameConstants.DEAFAULT_PLAYER_SPEED*0.5f);
        }else {
            playerX.setSpeed(GameConstants.DEAFAULT_PLAYER_SPEED);
        }
        //kiem tra va cham voi co
        if (collisionController.checkCollisionWithStatic(playerX,this.grasses)){
            //playerX.tangHinh();
            playerX.setHidden(false);
        } else {
            playerX.setHidden(true);
        }
        if (collisionController.checkCollision(playerX, gameModel.getPlayer(0))){
            playerX.undoMove();
        }
        else if (collisionController.checkCollision(playerX, gameModel.getPlayer(1))){
            playerX.undoMove();
        }
    }
    // Helper method to handle movement logic based on input
    private void handlePlayerMovement(IMovable playerX, int playerIndex) {
        Player player = (Player) playerX;
        float speed = player.getSpeed() > 0 ? player.getSpeed() : 3.0f; // Default speed if not set
        float dx = 0;
        float dy = 0;
        if (playerIndex == 0) { // Player 1 (WASD)
            if (inputController.isUpP1Pressed()) {
                dy -= speed;
                player.setImage(sprites.player1Up);
                player.setFace(2);
            }
            else if (inputController.isDownP1Pressed()) {
                dy += speed;
                player.setImage(sprites.player1Down);
                player.setFace(4);
            }
            else  if (inputController.isLeftP1Pressed()) {
                dx -= speed;
                player.setImage(sprites.player1Left);
                player.setFace(1);
            }
            else if (inputController.isRightP1Pressed()) {
                dx += speed;
                player.setImage(sprites.player1Right);
                player.setFace(3);
            }
        } else if (playerIndex == 1) { // Player 2 (Arrow Keys)
            if (inputController.isUpP2Pressed()) {
                dy -= speed;
                player.setImage(sprites.player2Up);
                player.setFace(2);
            }
            else if (inputController.isDownP2Pressed()) {
                dy += speed;
                player.setImage(sprites.player2Down);
                player.setFace(4);
            }
            else if (inputController.isLeftP2Pressed()) {
                dx -= speed;
                player.setImage(sprites.player2Left);
                player.setFace(1);
            }
            else if (inputController.isRightP2Pressed()) {
                dx += speed;
                player.setImage(sprites.player2Right);
                player.setFace(3);
            }
        }

        // Apply movement if there is any change
        if (dx != 0 || dy != 0) {
            player.storePreviousPosition();
            player.setX(player.getX() + dx);
            player.setY(player.getY() + dy);
            player.update();
            //System.out.println("Player " + (playerIndex + 1) + " moved to (" + player.getX() + ", " + player.getY() + ")"); // Debug

        }
    }


    @Override
    public boolean isRunning() {
        return running;
    }



}