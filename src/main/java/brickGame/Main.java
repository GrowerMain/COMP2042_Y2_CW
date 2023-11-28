package brickGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is the main entry point for the Block Game application.
 * It extends the JavaFX `Application` class and implements the `EventHandler<KeyEvent>`
 * and `GameEngine.OnAction` interfaces to handle keyboard events and game actions, respectively.
 * Example usage:
 * <pre>
 * {@code
 * public static void main(String[] args) {
 *     launch(args);
 * }
 * }
 * </pre>
 * </p>
 * <p>The game involves a ball, a paddle, and various blocks. The goal is to destroy the blocks
 * using the ball, controlled by the paddle, and advance through levels.
 *
 * @author Nicholas Lum
 * @version 1.0
 */

public class Main extends Application implements EventHandler<KeyEvent>, GameEngine.OnAction {

    // Constants

    // Instance variables

    // GUI components


    // Game settings
    private int level = 0;
    private int diffLevel = 4;


    // Paddle (break) dimensions
    private static final int ballRadius = 10;
    private static final int PADDLE_WIDTH     = 130;
    private static final int PADDLE_HEIGHT    = 30;
    private static final int HALF_PADDLE_WIDTH = PADDLE_WIDTH / 2;

    // Scene dimensions

    private static final int SCENE_WIDTH  = 500;
    private static final int SCENE_HEIGHT = 700;

    // Ball variables
    private Circle ball;
    private double xBall;
    private double yBall;
    private boolean isBallStuck = true;

    // Paddle variables
    private Rectangle rect;
    private double xBreak = 0.0f;
    private double yBreak = 640.0f;
    private double centerBreakX;


    // Game state variables
    private int destroyedBlockCount = 0;
    private double v;
    private int  heart    = 3;
    private int  score    = 0;
    private int scoreMultiplier = 1;
    private long time     = 0;
    private long hitTime  = 0;
    private long goldTime = 0;

    // Ball state variables
    private boolean goDownBall                  = true;
    private boolean goRightBall                 = true;
    private boolean collideToBreak               = false;
    private boolean collideToBreakAndMoveToRight = true;
    private boolean collideToRightWall           = false;
    private boolean collideToLeftWall            = false;
    private boolean collideToRightBlock          = false;
    private boolean collideToBottomBlock         = false;
    private boolean collideToLeftBlock           = false;
    private boolean collideToTopBlock            = false;

    // Ball velocity variables
    private double vX = 1.000;
    private double vY = 1.000;


    // Game engine and save path
    private GameEngine engine;
    public static String savePath    = "C:/save/save.mdds";
    public static String savePathDir = "C:/save/";

    // Block and color arrays
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private ArrayList<Bonus> chocoBlock = new ArrayList<Bonus>();
    private Color[] colors = new Color[]{
            Color.MAGENTA,
            Color.RED,
            Color.GOLD,
            Color.CORAL,
            Color.AQUA,
            Color.VIOLET,
            Color.GREENYELLOW,
            Color.ORANGE,
            Color.PINK,
            Color.SLATEGREY,
            Color.YELLOW,
            Color.TOMATO,
            Color.TAN,
    };

    // GUI components
    private Pane root;
    private Label scoreLabel;
    private Label heartLabel;
    private Label levelLabel;

    // Flags
    private boolean loadFromSave = false;

    // Buttons
    private Stage primaryStage;
    Button load    = null;
    Button newGame = null;
    /**=============================================================================================================
====================================================================================================================
====================================================================================================================
====================================================================================================================
====================================================================================================================*/

    private boolean isGoldStatus      = false;
    private boolean isExistHeartBlock = false;

    /**
     * The main entry point for the application.
     * Initializes the game and sets up the primary stage.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * public static void main(String[] args) {
     *     launch(args);
     * }
     * }
     * </pre>
     * </p>
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Represents the main game loop where physics and game state updates occur.
     * <p>
     * game update events and updates the UI elements.
     * </p>
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * engine = new GameEngine();
     * engine.setOnAction(this);
     * engine.setFps(120);
     * engine.start();
     * }
     * </pre>
     * </p>
     */
    // Start method for initializing the game
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;


        if (!loadFromSave) {
            level++;
            if (level >1){
                new Score().showMessage("Level Up :)", this);
            }
            if (level == 18) {
                new Score().showWin(this);
                return;
            }

            initBall();
            initBreak();
            initBoard();

            load = new Button("Load Game");
            newGame = new Button("Start New Game");
            load.setTranslateX(220);
            load.setTranslateY(300);
            newGame.setTranslateX(220);
            newGame.setTranslateY(340);

        }


        root = new Pane();
        scoreLabel = new Label("Score: " + score);
        levelLabel = new Label("Level: " + level);
        levelLabel.setTranslateY(20);
        heartLabel = new Label("Heart : " + heart);
        heartLabel.setTranslateX(SCENE_WIDTH  - 70);
        if (!loadFromSave) {
            root.getChildren().addAll(rect, ball, scoreLabel, heartLabel, levelLabel, newGame, load);
        } else {
            root.getChildren().addAll(rect, ball, scoreLabel, heartLabel, levelLabel);
        }
        for (Block block : blocks) {
            root.getChildren().add(block.rect);
        }
        Scene scene = new Scene(root, SCENE_WIDTH , SCENE_HEIGHT);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this);

        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        if (!loadFromSave) {
            if (level > 1 && level < 18) {
                load.setVisible(false);
                newGame.setVisible(false);
                engine = new GameEngine();
                engine.setOnAction(this);
                engine.setFps(120);
                engine.start();
            }

            load.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadGame();

                    load.setVisible(false);
                    newGame.setVisible(false);
                }
            });

            newGame.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    engine = new GameEngine();
                    engine.setOnAction(Main.this);
                    engine.setFps(120);
                    engine.start();

                    load.setVisible(false);
                    newGame.setVisible(false);
                }
            });
        } else {
            engine = new GameEngine();
            engine.setOnAction(this);
            engine.setFps(120);
            engine.start();
            loadFromSave = false;
        }


    }



    /**
     * Initialize the ball for the game.
     * <p>
     * This method is responsible for setting up and initializing the game ball.
     * It includes the creation of the ball object, setting its initial position,
     * and configuring any other properties necessary for its functioning within the game.
     * </p>
     */
    // GUI initialization methods
    private void initBall() {
        Random random = new Random();
        xBall = random.nextInt(SCENE_WIDTH ) + 1;
        yBall = random.nextInt(SCENE_HEIGHT - 200) + ((level + diffLevel) * Block.getHeight()) + 15;
        ball = new Circle();
        ball.setRadius(ballRadius);
        ball.setFill(new ImagePattern(new Image("ball.png")));

        // Set the initial ball state to stuck
        isBallStuck = true;
    }


    /**
     * Initialize the paddle (break) for the game.
     * <p>
     * This method is responsible for setting up and initializing the game paddle (break).
     * It includes the creation of the paddle object, setting its initial position,
     * and configuring any other properties necessary for its functioning within the game.
     * </p>
     */
    private void initBreak() {
        rect = new Rectangle();
        rect.setWidth(PADDLE_WIDTH);
        rect.setHeight(PADDLE_HEIGHT);
        rect.setX(xBreak);
        rect.setY(yBreak);

        ImagePattern pattern = new ImagePattern(new Image("block.jpg"));

        rect.setFill(pattern);
    }


    /**
     * Initialize the game board.
     * <p>
     * This method is responsible for setting up and initializing the game board.
     * It involves the creation of blocks, determining their positions, and configuring
     * any other properties necessary for the board's functioning within the game.
     * </p>
     */
    private void initBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < level + diffLevel; j++) {
                int r = new Random().nextInt(500);
                if (r % 5 == 0) {
                    continue;
                }
                int type;
                if (r % 10 == 1) {
                    type = Block.BLOCK_CHOCO;
                } else if (r % 10 == 2) {
                    if (!isExistHeartBlock) {
                        type = Block.BLOCK_HEART;
                        isExistHeartBlock = true;
                    } else {
                        type = Block.BLOCK_NORMAL;
                    }
                } else if (r % 10 == 3) {
                    type = Block.BLOCK_STAR;
                } else {
                    type = Block.BLOCK_NORMAL;
                }
                blocks.add(new Block(j, i, colors[r % (colors.length)], type));
                //System.out.println("colors " + r % (colors.length));
            }
        }
    }

    /**
     * Handles key events for player input, such as moving the paddle or saving the game.
     *
     * @param event The KeyEvent triggered by user input.
     */
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                move(Direction.LEFT);
                break;
            case RIGHT:
                move(Direction.RIGHT);
                break;
            case DOWN:
                // setPhysicsToBall();
                break;
            case S:
                saveGame();
                break;
            case SPACE:
                if (isBallStuck) {
                    // Release the ball if it's currently stuck
                    isBallStuck = false;
                    // Set initial velocity or any other behavior when releasing the ball
                    vX = 1.000;
                    goDownBall = true;
                }
                break;
            case ESCAPE:
                restartGame();
                break;
        }
    }


    /**
     * Moves the paddle (break) either to the left or right based on the provided direction.
     *
     * @param direction The direction of movement (LEFT or RIGHT).
     */
    private void move(Direction direction) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sleepTime = 4;
                int maxIterations = 30;

                for (int i = 0; i < maxIterations; i++) {
                    if (direction == Direction.RIGHT && xBreak == (SCENE_WIDTH - PADDLE_WIDTH) ||
                            direction == Direction.LEFT && xBreak == 0) {
                        return;
                    }

                    if (direction == Direction.RIGHT) {
                        xBreak++;
                    } else {
                        xBreak--;
                    }

                    centerBreakX = xBreak + HALF_PADDLE_WIDTH;

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (i >= 20) {
                        sleepTime = i;
                    }
                }
            }
        }).start();
    }


    /**
     * Apply physics to the ball's movement, including collisions with the paddle and blocks.
     * <p>
     * This method is responsible for updating the ball's position based on its current state and
     * handling collisions with the paddle, walls, and blocks. It incorporates the physics
     * logic for the ball's movement within the game.
     * </p>
     */
    private void setPhysicsToBall() {
        //v = ((time - hitTime) / 1000.000) + 1.000;

        //System.out.println("level of play is:" + level);
        if (level > 1) {
            if (isBallStuck) {
                // If the ball is stuck, update its position based on the paddle's position
                xBall = centerBreakX;
                yBall = yBreak - ballRadius;
            } else {
                // Ball physics when released
                if (goDownBall) {
                    yBall += vY;
                } else {
                    yBall -= vY;
                }

                if (goRightBall) {
                    xBall += vX;
                } else {
                    xBall -= vX;
                }

                // Rest of the existing ball physics code goes here...

                if (yBall <= 0) {
                    // Handle collision with the top wall
                    resetCollideFlags();
                    goDownBall = true;
                    return;
                }

                if (yBall >= SCENE_HEIGHT) {
                    // Handle collision with the bottom wall
                    goDownBall = false;
                    if (!isGoldStatus) {
                        // TODO game over logic
                        heart--;
                        new Score().show(SCENE_WIDTH  / 2, SCENE_HEIGHT / 2, -1, this);

                        if (heart == 0) {
                            new Score().showGameOver(this);
                            engine.stop();
                        }
                    }
                }
            }

        } else {
            // Handle behavior for levels other than 2
            // You can add different logic or leave it empty depending on your requirements


            if (goDownBall) {
                yBall += vY;
            } else {
                yBall -= vY;
            }

            if (goRightBall) {
                xBall += vX;
            } else {
                xBall -= vX;
            }

            if (yBall <= 0) {
                //vX = 1.000;
                resetCollideFlags();
                goDownBall = true;
                return;
            }
            if (yBall >= SCENE_HEIGHT) {
                goDownBall = false;
                if (!isGoldStatus) {
                    //TODO gameOver
                    heart--;
                    new Score().show(SCENE_WIDTH  / 2, SCENE_HEIGHT / 2, -1, this);

                    if (heart == 0) {
                        new Score().showGameOver(this);
                        engine.stop();
                    }

                }
                //return;
            }
        }

        // If gold ball status is active, increase score by 3 times
        if (isGoldStatus) {
            scoreMultiplier = 3;
        }else {
            scoreMultiplier = 1;
        }

        if (yBall >= yBreak - ballRadius) {
            //System.out.println("collide1");
            if (xBall >= xBreak && xBall <= xBreak + PADDLE_WIDTH) {
                hitTime = time;
                resetCollideFlags();
                goDownBall = false;

                double relation = (xBall - centerBreakX) / (PADDLE_WIDTH / 2);

                if (Math.abs(relation) <= 0.3) {
                    //vX = 0;
                    vX = Math.abs(relation);
                } else if (Math.abs(relation) > 0.3 && Math.abs(relation) <= 0.7) {
                    vX = (Math.abs(relation) * 1.5) + (level / 3.500);
                    //System.out.println("vX " + vX);
                } else {
                    vX = (Math.abs(relation) * 2) + (level / 3.500);
                    //System.out.println("vX " + vX);
                }

                if (xBall - centerBreakX > 0) {
                    collideToBreakAndMoveToRight = true;
                } else {
                    collideToBreakAndMoveToRight = false;
                }
                //System.out.println("collide2");
            }
        }

        if (xBall >= SCENE_WIDTH ) {
            resetCollideFlags();
            //vX = 1.000;
            collideToRightWall = true;
        }

        if (xBall <= 0) {
            resetCollideFlags();
            //vX = 1.000;
            collideToLeftWall = true;
        }

        if (collideToBreak) {
            if (collideToBreakAndMoveToRight) {
                goRightBall = true;
            } else {
                goRightBall = false;
            }
        }

        //Wall collide
        if (collideToRightWall) {
            goRightBall = false;
        }

        if (collideToLeftWall) {
            goRightBall = true;
        }

        //Block collide

        if (collideToRightBlock) {
            goRightBall = true;
        }

        if (collideToLeftBlock) {
            goRightBall = true;
        }

        if (collideToTopBlock) {
            goDownBall = false;
        }

        if (collideToBottomBlock) {
            goDownBall = true;
        }

    }


    /**
     * Check the count of destroyed blocks and trigger actions accordingly.
     * <p>
     * This method examines the count of destroyed blocks and initiates specific actions
     * based on the game's logic. It plays a role in determining whether the player has
     * successfully cleared a level or achieved certain conditions in the game.
     * </p>
     */
    private void checkDestroyedCount() {
        if (destroyedBlockCount == blocks.size()) {
            //TODO win level todo...
            //System.out.println("You Win");

            nextLevel();
        }
    }


    /**
     * Advance to the next level of the game.
     * <p>
     * This method handles the transition to the next level of the game. It includes
     * the necessary logic for resetting certain game parameters, updating the UI,
     * and initializing the game state for the new level.
     * </p>
     */
    private void nextLevel() {

        isBallStuck = true;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    vX = 1.000;

                    engine.stop();
                    resetCollideFlags();
                    goDownBall = true;

                    isGoldStatus = false;
                    isExistHeartBlock = false;


                    hitTime = 0;
                    time = 0;
                    goldTime = 0;

                    engine.stop();
                    blocks.clear();
                    chocoBlock.clear();
                    destroyedBlockCount = 0;
                    start(primaryStage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Save the current game state to a file.
     * <p>
     * This method is responsible for saving the current state of the game to a file.
     * It includes logic for persisting essential game parameters such as the level,
     * score, and player's progress.
     * </p>
     */
    private void saveGame() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new File(savePathDir).mkdirs();
                File file = new File(savePath);
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(new FileOutputStream(file));

                    outputStream.writeInt(level);
                    outputStream.writeInt(score);
                    outputStream.writeInt(heart);
                    outputStream.writeInt(destroyedBlockCount);


                    outputStream.writeDouble(xBall);
                    outputStream.writeDouble(yBall);
                    outputStream.writeDouble(xBreak);
                    outputStream.writeDouble(yBreak);
                    outputStream.writeDouble(centerBreakX);
                    outputStream.writeLong(time);
                    outputStream.writeLong(goldTime);
                    outputStream.writeDouble(vX);


                    outputStream.writeBoolean(isExistHeartBlock);
                    outputStream.writeBoolean(isGoldStatus);
                    outputStream.writeBoolean(goDownBall);
                    outputStream.writeBoolean(goRightBall);
                    outputStream.writeBoolean(collideToBreak);
                    outputStream.writeBoolean(collideToBreakAndMoveToRight);
                    outputStream.writeBoolean(collideToRightWall);
                    outputStream.writeBoolean(collideToLeftWall);
                    outputStream.writeBoolean(collideToRightBlock);
                    outputStream.writeBoolean(collideToBottomBlock);
                    outputStream.writeBoolean(collideToLeftBlock);
                    outputStream.writeBoolean(collideToTopBlock);

                    ArrayList<BlockSerializable> blockSerializables = new ArrayList<BlockSerializable>();
                    for (Block block : blocks) {
                        if (block.isDestroyed) {
                            continue;
                        }
                        blockSerializables.add(new BlockSerializable(block.row, block.column, block.type));
                    }

                    outputStream.writeObject(blockSerializables);

                    new Score().showMessage("Game Saved", Main.this);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    /**
     * Load the game state from a saved file.
     * <p>
     * This method handles loading the saved game state from a file. It includes
     * logic to restore the level, score, and other relevant game parameters.
     * </p>
     */
    private void loadGame() {

        LoadSave loadSave = new LoadSave();
        loadSave.read();
        isBallStuck = false;

        isExistHeartBlock = loadSave.isExistHeartBlock;
        isGoldStatus = loadSave.isGoldStatus;
        goDownBall = loadSave.goDownBall;
        goRightBall = loadSave.goRightBall;
        collideToBreak = loadSave.collideToBreak;
        collideToBreakAndMoveToRight = loadSave.collideToBreakAndMoveToRight;
        collideToRightWall = loadSave.collideToRightWall;
        collideToLeftWall = loadSave.collideToLeftWall;
        collideToRightBlock = loadSave.collideToRightBlock;
        collideToBottomBlock = loadSave.collideToBottomBlock;
        collideToLeftBlock = loadSave.collideToLeftBlock;
        collideToTopBlock = loadSave.collideToTopBlock;
        level = loadSave.level;
        score = loadSave.score;
        heart = loadSave.heart;
        destroyedBlockCount = loadSave.destroyedBlockCount;
        xBall = loadSave.xBall;
        yBall = loadSave.yBall;
        xBreak = loadSave.xBreak;
        yBreak = loadSave.yBreak;
        centerBreakX = loadSave.centerBreakX;
        time = loadSave.time;
        goldTime = loadSave.goldTime;
        vX = loadSave.vX;

        blocks.clear();
        chocoBlock.clear();

        for (BlockSerializable ser : loadSave.blocks) {
            int r = new Random().nextInt(200);
            blocks.add(new Block(ser.row, ser.j, colors[r % colors.length], ser.type));
        }


        try {
            loadFromSave = true;
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    /**
     * Reset collision flags.
     * <p>
     * This method resets the flags used to track collisions in the game. It is called
     * to clear collision-related state before re-evaluating collisions in the game loop.
     * </p>
     */
    private void resetCollideFlags() {

        collideToBreak = false;
        collideToBreakAndMoveToRight = false;
        collideToRightWall = false;
        collideToLeftWall = false;

        collideToRightBlock = false;
        collideToBottomBlock = false;
        collideToLeftBlock = false;
        collideToTopBlock = false;
    }


    /**
     * Restarts the game with initial settings.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * restartGame();
     * }
     * </pre>
     * </p>
     */
    public void restartGame() {

        try {
            level = 0;
            heart = 3;
            score = 0;
            vX = 1.000;
            destroyedBlockCount = 0;
            resetCollideFlags();
            goDownBall = true;

            isGoldStatus = false;
            isExistHeartBlock = false;
            hitTime = 0;
            time = 0;
            goldTime = 0;

            blocks.clear();
            chocoBlock.clear();

            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update the background based on the current game state.
     * <p>
     * This method is responsible for updating the background of the game based on
     * the current game state. It may change the background image or color to reflect
     * different levels or events in the game.
     * </p>
     */
    private void updateBackground() {
        if (score >= 20) {
            root.setStyle("-fx-background-image: url('TCC2.png');");
        }else if (score >= 10) {
            root.setStyle("-fx-background-image: url('TCC3.jpg');");
        } else {
            root.setStyle(null); // Remove background image
        }
    }


    /**
     * Update and render the game based on the current state.
     * <p>
     * Update UI elements based on game state.
     * </p>
     */
    @Override
    public void onUpdate() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                scoreLabel.setText("Score: " + score);
                heartLabel.setText("Heart : " + heart);

                rect.setX(xBreak);
                rect.setY(yBreak);
                ball.setCenterX(xBall);
                ball.setCenterY(yBall);

                for (Bonus choco : chocoBlock) {
                    choco.choco.setY(choco.y);
                }
                // Update the background based on the score
                updateBackground();
            }


        });


        if (yBall >= Block.getPaddingTop() && yBall <= (Block.getHeight() * (level + diffLevel)) + Block.getPaddingTop()) {
            for (final Block block : blocks) {
                int hitCode = block.checkHitToBlock(xBall, yBall);
                if (hitCode != Block.NO_HIT) {
                    score += (scoreMultiplier * 1);

                    new Score().show(block.x, block.y, 1, this);

                    block.rect.setVisible(false);
                    block.isDestroyed = true;
                    destroyedBlockCount++;
                    //System.out.println("size is " + blocks.size());
                    resetCollideFlags();

                    if (block.type == Block.BLOCK_CHOCO) {
                        final Bonus choco = new Bonus(block.row, block.column);
                        choco.timeCreated = time;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                root.getChildren().add(choco.choco);
                            }
                        });
                        chocoBlock.add(choco);
                    }

                    if (block.type == Block.BLOCK_STAR) {
                        goldTime = time;
                        ball.setFill(new ImagePattern(new Image("goldball.png")));
                        System.out.println("gold ball");
                        root.getStyleClass().add("goldRoot");
                        isGoldStatus = true;
                    }

                    if (block.type == Block.BLOCK_HEART) {
                        heart++;
                    }

                    if (hitCode == Block.HIT_RIGHT) {
                        collideToRightBlock = true;
                    } else if (hitCode == Block.HIT_BOTTOM) {
                        collideToBottomBlock = true;
                    } else if (hitCode == Block.HIT_LEFT) {
                        collideToLeftBlock = true;
                    } else if (hitCode == Block.HIT_TOP) {
                        collideToTopBlock = true;
                    }

                }

                //TODO hit to break and some work here....
                //System.out.println("Break in row:" + block.row + " and column:" + block.column + " hit");
            }
        }
    }


    /**
     * Initialization logic for the game engine.
     * <p>
     * This method contains initialization logic specific to the game engine. It is
     * called during the initialization phase of the game and may include setting
     * up the game loop, configuring the frame rate, or other engine-related tasks.
     * </p>
     */
    @Override
    public void onInit() {

    }


    /**
     * Handle physics-related updates during the game.
     * <p>
     * This method is called during the game loop to handle physics-related updates.
     * It may include calculations for the movement, collisions, or other physical
     * interactions in the game world.
     * </p>
     */
    @Override
    public void onPhysicsUpdate() {
        checkDestroyedCount();
        setPhysicsToBall();


        if (time - goldTime > 5000) {
            ball.setFill(new ImagePattern(new Image("ball.png")));
            root.getStyleClass().remove("goldRoot");
            isGoldStatus = false;
        }

        for (Bonus choco : chocoBlock) {
            if (choco.y > SCENE_HEIGHT || choco.taken) {
                continue;
            }
            if (choco.y >= yBreak && choco.y <= yBreak + PADDLE_HEIGHT && choco.x >= xBreak && choco.x <= xBreak + PADDLE_WIDTH) {
                System.out.println("You Got it and +3 score for you");
                choco.taken = true;
                choco.choco.setVisible(false);
                score += 3;
                new Score().show(choco.x, choco.y, 3, this);
            }
            choco.y += ((time - choco.timeCreated) / 1000.000) + 1.000;
        }

        //System.out.println("time is:" + time + " goldTime is " + goldTime);

    }



    /**
     * Handle the passage of time during the game.
     *
     * @param time The current time in milliseconds.
     */
    @Override
    public void onTime(long time) {
        this.time = time;
    }

}
