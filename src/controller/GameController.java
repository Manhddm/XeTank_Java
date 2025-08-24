package controller;

import controller.interfaces.IGameController;
import core.GameConstants;
import core.Sprites;
import model.GameModel;
import model.base.Direction;
import model.entities.*;
import model.interfaces.IEntity; 
import model.interfaces.IMovable; 
import view.rendering.GamePanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class GameController implements IGameController, ActionListener { // Implement ActionListener for Timer
    private GameModel gameModel;
    private CollisionController collisionController;
    private GamePanel gamePanel ;
    private KeyboardController inputController;
    private Sprites sprites = new Sprites();
    private Timer gameTimer; // Timer for the game loop
    private boolean running = false;
    private final int GAME_TICK_DELAY = 1000 / 60; // Approx 60 FPS
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
        this.walls = gameModel.getStaticEntitiesOfType(Wall.class);
        this.waters = gameModel.getStaticEntitiesOfType(Water.class);
        this.grasses = gameModel.getStaticEntitiesOfType(Grass.class);
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
        if (player1Entity != null) { // Check if the entity is movable
            Player player1 = (Player) player1Entity;
            handlePlayerMovement(player1, 0); // Pass player index 0
            handlePlayerCollision(player1);
            handelPlayerShooting(player1);
        }
        // --- Player 2 Input ---
        IEntity player2Entity = gameModel.getPlayer(1); // Assuming index 1 for player 2
        if (player2Entity != null) { // Check if the entity is movable
            Player player2 = (Player) player2Entity;
            handlePlayerMovement(player2, 1); // Pass player index 1
            handlePlayerCollision(player2);
            handelPlayerShooting(player2);
        }
        handleBulletCollision();

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
    }
    void handelPlayerShooting(Player player) {
        int playerIndex =   player == gameModel.getPlayer(0)?0:1;

        if (inputController.isPlayerShooting(playerIndex)) {

            Bullet bullet = player.shoot();
            if (bullet != null) {
                bullet.setImage(sprites.bullet);
                gameModel.addBullet(bullet);
                // Reset trạng thái bắn ngay lập tức nếu muốn (hoặc giữ lại để kiểm tra nút giữ)
                if (playerIndex == 0) {
                    inputController.setShootP1(false);
                } else {
                    inputController.setShootP2(false);
                }

                System.out.println("Player " + (playerIndex + 1) + " shot a bullet");
            }
        }
    }
    void handleBulletCollision() {
        List<Bullet> bullets = gameModel.getBullets();
        for (Bullet bullet : bullets) {
            if (collisionController.checkCollisionWithStatic(bullet,this.walls)){
                bullet.setDead(true);
            }
        }
    }
    //Check Collision
    void handlePlayerCollision(Player player) {
        
        //Kiem tra va cham voi tuong khi di chuyen
        //List<Wall> walls = gameModel.getEntitiesOfType(Wall.class);
        if (collisionController.checkCollisionWithStatic(player,this.walls)){
            player.undoMove();
        }
        //Kiem Tra va cham voi nuoc
        // List<Water> waters = gameModel.getEntitiesOfType(Water.class);
        if (collisionController.checkCollisionWithStatic(player,this.waters)){
            player.setSpeed(GameConstants.DEAFAULT_PLAYER_SPEED - GameConstants.DEAFAULT_PLAYER_SPEED*0.5f);
        }else {
            player.setSpeed(GameConstants.DEAFAULT_PLAYER_SPEED);
        }
        //kiem tra va cham voi co
        if (collisionController.checkCollisionWithStatic(player,this.grasses)){
            player.setHidden(false);
        } else {
            player.setHidden(true);
        }
        if (collisionController.checkCollision(player, gameModel.getPlayer(0))){
            player.undoMove();
        }
        else if (collisionController.checkCollision(player, gameModel.getPlayer(1))){
            player.undoMove();
        }
        //kiem tra va tram dan
        List<Bullet> bullets = gameModel.getBullets();
        for (Bullet bullet : bullets) {
            if (collisionController.checkCollision(bullet,player)) {
                bullet.setDead(true);
                player.setLives(player.getLives() - bullet.getDamage());
                break;
            }
        }

    }
    // Helper method to handle movement logic based on input
    private void handlePlayerMovement(IMovable playerX, int playerIndex) {
        Player player = (Player) playerX;
        player.storePreviousPosition();
        if (inputController.isUpPressed(playerIndex)) {
            player.move(Direction.UP);
            player.setImage(playerIndex==0?sprites.player1Up:sprites.player2Up);
        }
        else if (inputController.isDownPressed(playerIndex)) {
            player.move(Direction.DOWN);
            player.setImage(playerIndex==0?sprites.player1Down:sprites.player2Down);
        }
        else if (inputController.isLeftPressed(playerIndex)) {
            player.move(Direction.LEFT);
            player.setImage(playerIndex==0?sprites.player1Left:sprites.player2Left);
        }
        else if (inputController.isRightPressed(playerIndex)) {
            player.move(Direction.RIGHT);
            player.setImage(playerIndex==0?sprites.player1Right:sprites.player2Right);
        }
        player.update();
    }
    @Override
    public boolean isRunning() {
        return running;
    }



}