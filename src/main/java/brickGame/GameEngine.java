package brickGame;

/**
 * The {@code GameEngine} class represents the game engine responsible for managing game updates,
 * physics calculations, and time tracking.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * Example usage:
 * </p>
 * <pre>
 * {@code
 * GameEngine engine = new GameEngine();
 * engine.setOnAction(onActionImplementation);
 * engine.setFps(60);
 * engine.start();
 * }
 * </pre>
 */
public class GameEngine {

    /**
     * The default frames per second (FPS) value.
     */
    private int DEFAULT_FPS = 15;


    /**
     * The interface defining actions to be performed by the game engine.
     */
    public interface OnAction {
        /**
         * Called during each game update.
         */
        void onUpdate();

        /**
         * Called during the initialization phase.
         */
        void onInit();

        /**
         * Called during physics calculations.
         */
        void onPhysicsUpdate();

        /**
         * Called to update the game time.
         *
         * @param time The current time in the game.
         */
        void onTime(long time);
    }

    private OnAction onAction;
    private int fps = 1000 / DEFAULT_FPS;
    private volatile boolean isStopped = true;

    private Thread updateThread;
    private Thread physicsThread;
    private Thread timeThread;

    private long time = 0;

    /**
     * Sets the action handler for the game engine.
     *
     * @param onAction The action handler implementing the OnAction interface.
     */
    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }




    /**
     * Sets the frames per second (FPS) for the game engine.
     *
     * @param fps The desired frames per second.
     */
    public void setFps(int fps) {
        this.fps = 1000 / fps;
    }

    /**
     * Starts a new thread for game updates. The {@code onUpdate} method of the assigned
     * {@code OnAction} handler is called repeatedly with a delay determined by the frames
     * per second (FPS) value.
     */
    private void Update() {
        updateThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    onAction.onUpdate();
                    Thread.sleep(fps);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                }
            }
        });
        updateThread.start();
    }

    /**
     * Initializes the game. The {@code onInit} method of the assigned {@code OnAction} handler
     * is called once at the start of the game.
     */
    private void Initialize() {
        onAction.onInit();
    }

    /**
     * Starts a new thread for physics calculations. The {@code onPhysicsUpdate} method of the
     * assigned {@code OnAction} handler is called repeatedly with a delay determined by the
     * frames per second (FPS) value.
     */
    private void PhysicsCalculation() {
        physicsThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    onAction.onPhysicsUpdate();
                    Thread.sleep(fps);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                }
            }
        });
        physicsThread.start();
    }

    /**
     * Starts the game engine.
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
     * Stops the game engine.
     */
    public void stop() {
        if (!isStopped) {
            isStopped = true;
            updateThread.interrupt();
            physicsThread.interrupt();
            timeThread.interrupt();
        }
    }

    private void TimeStart() {
        timeThread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    time++;
                    onAction.onTime(time);
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
            }
        });
        timeThread.start();
    }


}

