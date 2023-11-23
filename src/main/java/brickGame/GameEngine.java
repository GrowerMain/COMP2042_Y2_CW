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
    private volatile boolean isStopped = true;

    private Thread updateThread;
    private Thread physicsThread;
    private Thread timeThread;

    private long time = 0;

    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }

    public void setFps(int fps) {
        this.fps = 1000 / fps;
    }

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

    private void Initialize() {
        onAction.onInit();
    }

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

    public void start() {
        time = 0;
        Initialize();
        Update();
        PhysicsCalculation();
        TimeStart();
        isStopped = false;
    }

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

    public interface OnAction {
        void onUpdate();

        void onInit();

        void onPhysicsUpdate();

        void onTime(long time);
    }
}

