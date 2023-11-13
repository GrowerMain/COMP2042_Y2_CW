package brickGame;

/**
 * The {@code GameEngine} class represents the game engine responsible for managing game updates,
 * physics calculations, and time tracking.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * GameEngine engine = new GameEngine();
 * engine.setOnAction(onActionImplementation);
 * engine.setFps(60);
 * engine.start();
 * }
 * </pre>
 * </p>
 */
public class GameEngine {

    private OnAction onAction;
    private int fps = 15;
    private Thread updateThread;
    private Thread physicsThread;
    public boolean isStopped = true;

    /**
     * Sets the {@code OnAction} listener for handling game-related events.
     *
     * @param onAction The implementation of the {@code OnAction} interface.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * engine.setOnAction(onActionImplementation);
     * }
     * </pre>
     * </p>
     */
    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }

    /**
     * @param fps set fps and we convert it to millisecond
     */
    public void setFps(int fps) {
        this.fps = (int) 1000 / fps;
    }

    /**
     * Sets the frames per second (fps) for the game engine.
     *
     * @param fps The desired frames per second.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * engine.setFps(60);
     * }
     * </pre>
     * </p>
     *
     * @implNote The fps value is converted to milliseconds for internal use.
     */
    private synchronized void Update() {
        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!updateThread.isInterrupted()) {
                    try {
                        onAction.onUpdate();
                        Thread.sleep(fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        updateThread.start();
    }

    private void Initialize() {
        onAction.onInit();
    }

    private synchronized void PhysicsCalculation() {
        physicsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!physicsThread.isInterrupted()) {
                    try {
                        onAction.onPhysicsUpdate();
                        Thread.sleep(fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        physicsThread.start();

    }

    /**
     * Starts the game engine, initializing the game state and initiating update and physics threads.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * engine.start();
     * }
     * </pre>
     * </p>
     */
    public void start() {
        time = 0;
        Initialize();
        Update();
        PhysicsCalculation();
        TimeStart();
        isStopped = false;
    }

    /**
     * Stops the game engine, interrupting update, physics, and time threads.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * engine.stop();
     * }
     * </pre>
     * </p>
     */
    public void stop() {
        if (!isStopped) {
            isStopped = true;
            updateThread.stop();
            physicsThread.stop();
            timeThread.stop();
        }
    }

    private long time = 0;

    private Thread timeThread;

    private void TimeStart() {
        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        time++;
                        onAction.onTime(time);
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timeThread.start();
    }


    /**
     * Represents the callback interface for game-related events.
     */
    public interface OnAction {
        /**
         * Called during each game update to handle game logic.
         */
        void onUpdate();

        /**
         * Called during game initialization to set up initial game state.
         */
        void onInit();

        /**
         * Called during each physics update to handle physics calculations.
         */
        void onPhysicsUpdate();

        /**
         * Called continuously to provide the current time elapsed since the start of the game.
         *
         * @param time The elapsed time in milliseconds.
         */
        void onTime(long time);
    }

}
