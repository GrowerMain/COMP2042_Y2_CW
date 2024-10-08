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
 * <p>This class is the main entry point for the Block Game application.
 * It extends the JavaFX `Application` class and implements the `EventHandler`
 * and `GameEngine.OnAction` interfaces to handle keyboard events and game actions, respectively.
 * Example usage:
 * </p>
 * <pre>
 * {@code
 * public static void main(String[] args) {
 *     launch(args);
 * }
 * }
 * </pre>
 * <p>The game involves a ball, a paddle, and various blocks. The goal is to destroy the blocks
 * using the ball, controlled by the paddle, and advance through levels.
 * </p>
 *
 * @author Nicholas Lum
 * @version 1.0
 */
public class Main extends Application implements EventHandler<KeyEvent>, GameEngine.OnAction {

    // Game settings
    private static final int victoryLevel = 22;
    /**
     * The radius of the ball in the game.
     */
    private static final int ballRadius = 10;
    /**
     * The width of the paddle (break) in the game.
     */
    private static final int PADDLE_WIDTH = 130;
    /**
     * The height of the paddle (break) in the game.
     */
    private static final int PADDLE_HEIGHT = 30;

    // Paddle (break) dimensions
    /**
     * Half of the width of the paddle (break) in the game.
     */
    private static final int HALF_PADDLE_WIDTH = PADDLE_WIDTH / 2;
    /**
     * The width of the game scene.
     */
    private static final int SCENE_WIDTH = 500;
    /**
     * The height of the game scene.
     */
    private static final int SCENE_HEIGHT = 700;
    /**
     * The path to save the game data.
     */
    public static String savePath = "C:/save/save.mdds";

    // Scene dimensions
    /**
     * The directory path to save the game data.
     */
    public static String savePathDir = "C:/save/";
    /**
     * The array of colors used for blocks in the game.
     */
    private final Color[] colors = new Color[]{
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

    // Ball variables
    /**
     * The primary stage of the JavaFX application.
     */
    public Stage primaryStage;
    int powerUp = 5;
    /**
     * The "Load Game" button in the GUI.
     */
    Button load = null;
    /**
     * The "New Game" button in the GUI.
     */
    Button newGame = null;

    // Paddle variables
    /**
     * The current level of the game.
     */
    private int level = 0;
    /**
     * The difficulty level of the game.
     */
    private final int diffLevel = 4;
    /**
     * The ball object in the game.
     */
    private Circle ball;
    /**
     * The x-coordinate of the ball.
     */
    private double xBall;


    // Game state variables
    /**
     * The y-coordinate of the ball.
     */
    private double yBall;
    /**
     * Indicates whether the ball is stuck.
     */
    private boolean isBallStuck = true;
    /**
     * The rectangle representing the paddle (break) in the game.
     */
    private Rectangle rect;
    /**
     * The x-coordinate of the paddle (break).
     */
    private double xBreak = 0.0f;
    /**
     * The y-coordinate of the paddle (break).
     */
    private double yBreak = 640.0f;
    /**
     * The center x-coordinate of the paddle (break).
     */
    private double centerBreakX;
    /**
     * The count of destroyed blocks in the game.
     */
    private int destroyedBlockCount = 0;

    // Ball state variables
    /**
     * The number of remaining heart lives.
     */
    private int heart = 3;
    /**
     * The score in the game.
     */
    private int score = 0;
    /**
     * The multiplier for the score.
     */
    private int scoreMultiplier = 1;
    /**
     * The current time in the game.
     */
    private long time = 0;
    /**
     * The time when the ball hits an object.
     */
    private long hitTime = 0;
    /**
     * The time when the gold status is active.
     */
    private long goldTime = 0;
    /**
     * Indicates the direction of the ball (downward).
     */
    private boolean goDownBall = true;
    /**
     * Indicates the direction of the ball (rightward).
     */
    private boolean goRightBall = true;
    /**
     * Indicates collision with the paddle (break).
     */
    private boolean collideToBreak = false;
    /**
     * Indicates collision with the paddle (break) and moving to the right.
     */
    private boolean collideToBreakAndMoveToRight = true;

    // Ball velocity variables
    /**
     * Indicates collision with the right wall.
     */
    private boolean collideToRightWall = false;


    // Game engine and save path
    /**
     * Indicates collision with the left wall.
     */
    private boolean collideToLeftWall = false;
    /**
     * Indicates collision with a block on the right.
     */
    private boolean collideToRightBlock = false;
    /**
     * Indicates collision with a block at the bottom.
     */
    private boolean collideToBottomBlock = false;
    /**
     * Indicates collision with a block on the left.
     */
    private boolean collideToLeftBlock = false;
    /**
     * Indicates collision with a block at the top.
     */
    private boolean collideToTopBlock = false;
    /**
     * The velocity of the ball in the x-direction.
     */
    private double vX = 1.000;

    // GUI components
    /**
     * The game engine for managing game logic.
     */
    private GameEngine engine;
    /**
     * The list of blocks in the game.
     */
    private final ArrayList<Block> blocks = new ArrayList<Block>();
    /**
     * The list of bonus (choco) blocks in the game.
     */
    private final ArrayList<Bonus> chocoBlock = new ArrayList<Bonus>();
    /**
     * The root pane of the GUI.
     */
    private Pane root;
    /**
     * The label displaying the score in the GUI.
     */
    private Label scoreLabel;

    // Flags
    /**
     * The label displaying the remaining heart lives in the GUI.
     */
    private Label heartLabel;

    // Buttons
    private Label powerLabel;
    /**
     * The label displaying the current level in the GUI.
     */
    private Label levelLabel;
    /**
     * Indicates whether to load the game state from a save.
     */
    private boolean loadFromSave = false;
    /**
     * Indicates whether the gold status is active.
     */
    private boolean isGoldStatus = false;

    /**
     * Indicates whether a heart block exists in the game.
     */
    private boolean isExistHeartBlock = false;

    private SoundPlayer startPlayer;
    private SoundPlayer chocoPlayer;
    private SoundPlayer heartPlayer;
    private SoundPlayer starPlayer;
    private SoundPlayer winPlayer;


    /**
     * The main entry point for the application.
     * Initializes the game and sets up the primary stage.
     * <p>
     * Example usage:
     * </p>
     * <pre>
     * {@code
     * public static void main(String[] args) {
     *     launch(args);
     * }
     * }
     * </pre>
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
     * </p>
     * <pre>
     * {@code
     * engine = new GameEngine();
     * engine.setOnAction(this);
     * engine.setFps(120);
     * engine.start();
     * }
     * </pre>
     */
    // Start method for initializing the game
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        // Initialize the SoundPlayer with the sound file path
        String playStartSound = "src/main/resources/start.mp3";
        String playChocoSound = "src/main/resources/choco.mp3";
        String playHeartSound = "src/main/resources/heart.mp3";
        String playStarSound = "src/main/resources/star.mp3";
        String playWinSound = "src/main/resources/levelup.mp3";

        startPlayer = new SoundPlayer(playStartSound);
        chocoPlayer = new SoundPlayer(playChocoSound);
        heartPlayer = new SoundPlayer(playHeartSound);
        starPlayer = new SoundPlayer(playStarSound);
        winPlayer = new SoundPlayer(playWinSound);

        // Play the sound using the SoundPlayer
        startPlayer.play();

        if (!loadFromSave) {
            level++;
            winPlayer.play();

            if (level == 1) {
                new Score().showMessage("Where am I?", this.primaryStage);
            }
            if (level == 2) {
                new Score().showMessage("Looks like I am gaining more power and growing as you destroy blocks, keep going!", this.primaryStage);
            }
            if (level == 3) {
                new Score().showMessage("What is this place? Am I in London? I sense something is not right...", this.primaryStage);
            }
            if (level == 4) {
                new Score().showMessage("It was an ILLUSION? We are under attack! Break more blocks so I can get stronger!", this.primaryStage);
            }
            if (level == 5) {
                new Score().showMessage("The city is not safe anymore, we need to go to the airport as soon as possible", this.primaryStage);
            }
            if (level == 6) {
                new Score().showMessage("The airport is not damaged yet! Lets hurry up!", this.primaryStage);
            }
            if (level == 7) {
                new Score().showMessage("What is that red light? Is it the aliens... we need to board a plane FAST!!", this.primaryStage);
            }
            if (level == 8) {
                new Score().showMessage("What is this green light? What is happening? HELPPPP", this.primaryStage);
            }
            if (level == 9) {
                new Score().showMessage("What just happened? Am I at the north pole? Those are the aurora lights! They are so pretty!! I am here for a purpose, lets go into that building and investigate further", this.primaryStage);
            }
            if (level == 10) {
                new Score().showMessage("Is that a rocket? I have never flown in one of those! Looks like the aliens are catching up, I guess there is a first for everything! :)", this.primaryStage);
            }
            if (level == 11) {
                new Score().showMessage("Andddddd... LIFTOFF!!! THIS IS AMAZING!!!!", this.primaryStage);
            }
            if (level == 12) {
                new Score().showMessage("Wait...Wait...Its too fast...Slow Down!!", this.primaryStage);
            }
            if (level == 13) {
                new Score().showMessage("Did I just blackou... woah IS THAT EARTH???", this.primaryStage);
            }
            if (level == 14) {
                new Score().showMessage("This is SO BEAUTIFUL!", this.primaryStage);
            }
            if (level == 15) {
                new Score().showMessage("I guess we have passed the moon now... where is this rocket taking me?", this.primaryStage);
            }
            if (level == 16) {
                new Score().showMessage("What was that flash...DID EARTH JUST EXPLODE?!?!?!?!", this.primaryStage);
            }
            if (level == 17) {
                new Score().showMessage("I see another ship in space! That must be the culprit! Lets follow it...but out fuel is running out! Nooo it's getting away!", this.primaryStage);
            }
            if (level == 18) {
                new Score().showMessage("Hey look there is another ship in space! It must be from the same fleet! Lets try to get on that one before it flies off too!", this.primaryStage);
            }
            if (level == 19) {
                new Score().showMessage("That was close...we almost didn't make it. We are in a completely different galaxy now... Is this their home?", this.primaryStage);
            }
            if (level == 20) {
                new Score().showMessage("We need to avenge our fallen planet, lets follow them into the portal!", this.primaryStage);
            }
            if (level == 21) {
                new Score().showMessage("Is that....god? NONO IT CANT BE!! EVERYTHING WE BELIEVED IN WAS A LIE!!", this.primaryStage);
            }
            if (level == 22) {
                new Score().showMessage("You have won my child, now rest in peace knowing your people have been avenged.", this.primaryStage);
            }
            if (level >= victoryLevel) {
                new Score().showWin(this.primaryStage);
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
        heartLabel.setTranslateX(SCENE_WIDTH - 70);
        powerLabel = new Label("Special : " + powerUp);
        powerLabel.setTranslateX(SCENE_WIDTH - 80);
        powerLabel.setTranslateY(20);
        if (!loadFromSave) {
            root.getChildren().addAll(rect, ball, scoreLabel, heartLabel, levelLabel, powerLabel, newGame, load);
        } else {
            root.getChildren().addAll(rect, ball, scoreLabel, heartLabel, levelLabel, powerLabel);
        }
        for (Block block : blocks) {
            root.getChildren().add(block.rect);
        }
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
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
        xBall = random.nextInt(SCENE_WIDTH) + 1;
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
            case W:
                level++;
                if (level == 1) {
                    new Score().showMessage("Where am I?", this.primaryStage);
                }
                if (level == 2) {
                    new Score().showMessage("Looks like I am gaining more power and growing as you destroy blocks, keep going!", this.primaryStage);
                }
                if (level == 3) {
                    new Score().showMessage("What is this place? Am I in London? I sense something is not right...", this.primaryStage);
                }
                if (level == 4) {
                    new Score().showMessage("It was an ILLUSION? We are under attack! Break more blocks so I can get stronger!", this.primaryStage);
                }
                if (level == 5) {
                    new Score().showMessage("The city is not safe anymore, we need to go to the airport as soon as possible", this.primaryStage);
                }
                if (level == 6) {
                    new Score().showMessage("The airport is not damaged yet! Lets hurry up!", this.primaryStage);
                }
                if (level == 7) {
                    new Score().showMessage("What is that red light? Is it the aliens... we need to board a plane FAST!!", this.primaryStage);
                }
                if (level == 8) {
                    new Score().showMessage("What is this green light? What is happening? HELPPPP", this.primaryStage);
                }
                if (level == 9) {
                    new Score().showMessage("What just happened? Am I at the north pole? Those are the aurora lights! They are so pretty!! L ets go into that building and investigate further", this.primaryStage);
                }
                if (level == 10) {
                    new Score().showMessage("Is that a rocket? I have never flown in one of those! Looks like the aliens are catching up, I guess there is a first for everything! :)", this.primaryStage);
                }
                if (level == 11) {
                    new Score().showMessage("Andddddd... LIFTOFF!!! THIS IS AMAZING!!!!", this.primaryStage);
                }
                if (level == 12) {
                    new Score().showMessage("Wait...Wait...Its too fast...Slow Down!!", this.primaryStage);
                }
                if (level == 13) {
                    new Score().showMessage("Did I just blackou... woah IS THAT EARTH???", this.primaryStage);
                }
                if (level == 14) {
                    new Score().showMessage("This is SO BEAUTIFUL!", this.primaryStage);
                }
                if (level == 15) {
                    new Score().showMessage("I guess we have passed the moon now... where is this rocket taking me?", this.primaryStage);
                }
                if (level == 16) {
                    new Score().showMessage("What was that flash...DID EARTH JUST EXPLODE?!?!?!?!", this.primaryStage);
                }
                if (level == 17) {
                    new Score().showMessage("I see another ship in space! That must be the culprit! Lets follow it...but out fuel is running out! Nooo it's getting away!", this.primaryStage);
                }
                if (level == 18) {
                    new Score().showMessage("Hey look there is another ship in space! It must be from the same fleet! Lets try to get on that one before it flies off too!", this.primaryStage);
                }
                if (level == 19) {
                    new Score().showMessage("That was close...we almost didn't make it. We are in a completely different galaxy now... Is this their home?", this.primaryStage);
                }
                if (level == 20) {
                    new Score().showMessage("We need to avenge our fallen planet, lets follow them into the portal!", this.primaryStage);
                }
                if (level == 21) {
                    new Score().showMessage("Is that....god? NONO IT CANT BE!! EVERYTHING WE BELIEVED IN WAS A LIE!!", this.primaryStage);
                }
                if (level == 22) {
                    new Score().showMessage("You have won my child, now rest in peace knowing your people have been avenged.", this.primaryStage);
                }
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
            case C:
                if (powerUp > 0) {
                    vX = 2.000;
                    goDownBall = !goDownBall;
                    powerUp--;
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
        //The velocity of the ball in the y-direction.

        double vY = 1.000;
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
                        new Score().show((double) SCENE_WIDTH / 2, (double) SCENE_HEIGHT / 2, -1, this.primaryStage);

                        if (heart == 0) {
                            new Score().showGameOver(primaryStage, score);
                            engine.stop();
                        }
                    }
                }
            }

        } else {


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
                    isBallStuck = true;
                    System.out.println("isBAllStuck is true.");
                    new Score().show((double) SCENE_WIDTH / 2, (double) SCENE_HEIGHT / 2, -1, this.primaryStage);

                    if (heart == 0) {
                        new Score().showGameOver(this.primaryStage, score);
                        engine.stop();
                    }

                }
                //return;
            }
        }

        // If gold ball status is active, increase score by 3 times
        if (isGoldStatus) {
            scoreMultiplier = 3;
        } else {
            scoreMultiplier = 1;
        }

        if (isBallStuck) {
            // If the ball is stuck, update its position based on the paddle's position
            xBall = centerBreakX;
            yBall = yBreak - ballRadius;
        }

        if (yBall >= yBreak - ballRadius) {
            //System.out.println("collide1");
            if (xBall >= xBreak && xBall <= xBreak + PADDLE_WIDTH) {
                hitTime = time;
                resetCollideFlags();
                goDownBall = false;

                double relation = (xBall - centerBreakX) / ((double) PADDLE_WIDTH / 2);

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

                collideToBreakAndMoveToRight = xBall - centerBreakX > 0;
                //System.out.println("collide2");
            }
        }

        if (xBall >= SCENE_WIDTH) {
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
            goRightBall = collideToBreakAndMoveToRight;
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
                        blockSerializables.add(new BlockSerializable(block.row, block.column, block.blockType));
                    }

                    outputStream.writeObject(blockSerializables);

                    new Score().showMessage("Game Saved", primaryStage);


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
     * </p>
     * <pre>
     * {@code
     * restartGame();
     * }
     * </pre>
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
        if (level == 1) {
            root.setStyle("-fx-background-image: url('bg1.jpg');");
        }
        if (level == 2) {
            root.setStyle("-fx-background-image: url('bg2.png');");
        }
        if (level == 3) {
            root.setStyle("-fx-background-image: url('bg3.jpg');");
        }
        if (level == 4) {
            root.setStyle("-fx-background-image: url('bg4.jpg');");
        }
        if (level == 5) {
            root.setStyle("-fx-background-image: url('bg5.jpg');");
        }
        if (level == 6) {
            root.setStyle("-fx-background-image: url('bg6.jpg');");
        }
        if (level == 7) {
            root.setStyle("-fx-background-image: url('bg7.png');");
        }
        if (level == 8) {
            root.setStyle("-fx-background-image: url('bg8.jpg');");
        }
        if (level == 9) {
            root.setStyle("-fx-background-image: url('bg9.jpg');");
        }
        if (level == 10) {
            root.setStyle("-fx-background-image: url('bg10.png');");
        }
        if (level == 11) {
            root.setStyle("-fx-background-image: url('bg11.png');");
        }
        if (level == 12) {
            root.setStyle("-fx-background-image: url('bg12.png');");
        }
        if (level == 13) {
            root.setStyle("-fx-background-image: url('bg13.jpg');");
        }
        if (level == 14) {
            root.setStyle("-fx-background-image: url('bg14.png');");
        }
        if (level == 15) {
            root.setStyle("-fx-background-image: url('bg15.jpg');");
        }
        if (level == 16) {
            root.setStyle("-fx-background-image: url('bg16.jpg');");
        }
        if (level == 17) {
            root.setStyle("-fx-background-image: url('bg17.jpg');");
        }
        if (level == 18) {
            root.setStyle("-fx-background-image: url('bg18.jpg');");
        }
        if (level == 19) {
            root.setStyle("-fx-background-image: url('bg19.jpg');");
        }
        if (level == 20) {
            root.setStyle("-fx-background-image: url('bg20.jpg');");
        }
        if (level == 21) {
            root.setStyle("-fx-background-image: url('bg21.jpg');");
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
                powerLabel.setText("Special : " + powerUp);

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
                    score += (scoreMultiplier);

                    new Score().show(block.xCoordinate, block.yCoordinate, 1, this.primaryStage);

                    block.rect.setVisible(false);
                    block.isDestroyed = true;
                    destroyedBlockCount++;
                    //System.out.println("size is " + blocks.size());
                    resetCollideFlags();

                    if (block.blockType == Block.BLOCK_CHOCO) {
                        final Bonus choco = new Bonus(block.row, block.column);
                        choco.timeCreated = time;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                root.getChildren().add(choco.choco);
                            }
                        });
                        chocoPlayer.play();
                        chocoBlock.add(choco);
                    }

                    if (block.blockType == Block.BLOCK_STAR) {
                        goldTime = time;
                        ball.setFill(new ImagePattern(new Image("goldball.png")));
                        System.out.println("gold ball");
                        root.getStyleClass().add("goldRoot");
                        isGoldStatus = true;
                        powerUp++;
                        starPlayer.play();
                    }

                    if (block.blockType == Block.BLOCK_HEART) {
                        heart++;
                        powerUp += 5;
                        heartPlayer.play();
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
                new Score().show(choco.x, choco.y, 3, this.primaryStage);
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
