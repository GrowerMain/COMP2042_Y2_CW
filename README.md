# COMP2042_CW_hfynl1
Coursework for Developing Maintainable Software

# Block Game Application

This is a simple Block Game application built with JavaFX.

## Prerequisites

Before you compile and run the application, make sure you have the following installed:

1. **Java Development Kit (JDK):**

   Download and install the latest version of JDK from the official Oracle website: [Java SE Downloads](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **JavaFX SDK:**

   JavaFX is bundled with JDK 8 and later versions. No separate installation is required.

3. **Git:**

   Download and install Git from the official Git website: [Git Downloads](https://git-scm.com/downloads)

4. **IntelliJ IDEA Community Edition:**

   Download and install IntelliJ IDEA Community Edition, a free and open-source version of IntelliJ IDEA:

    - Download: [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/)

## Opening the Project in IntelliJ IDEA

1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/block-game.git
    cd block-game
    ```

2. **Open the project in IntelliJ IDEA:**

    - Open IntelliJ IDEA.
    - Choose `Open` from the main menu.
    - Navigate to the `block-game` directory and select it.

3. **Configure JavaFX SDK in IntelliJ IDEA:**

    - Go to `File > Project Structure > Project`.
    - Set the `Project SDK` to your installed JDK version.
    - Set the `Project language level` to the appropriate level.

    - Go to `File > Project Structure > Libraries`.
    - Click the `+` button and add the path to your JavaFX SDK as a library.

4. **Run the Application in IntelliJ IDEA:**

    - Open the `Main.java` file located at `src/brickGame/Main.java`.
    - Right-click anywhere in the code editor and select `Run 'Main.main()'`.

## Controls

- Use the left and right arrow keys to move the paddle.
- Press the space key to release the ball.
- Press 'S' to save the game.
- Press 'ESC' to restart the game.

## Additional Notes

- This application uses JavaFX for the GUI. Ensure that you have the JavaFX SDK correctly configured.

- If you encounter any issues or errors, please check the console output for error messages and ensure that all dependencies are correctly set up.

Feel free to customize this README according to the specifics of your application and development environment.

# Implemented Features

The following features have been successfully implemented and are functioning as expected in the Block Game application:

1. **Game Initialization**
    - The game initializes with a paddle, ball, and blocks.
    - Initial game state is set up, including the player's lives, score, and level.

2. **Paddle Movement**
    - The paddle can be moved left and right using the arrow keys.

3. **Ball Physics**
    - The ball exhibits physics-based movement.
    - Collisions with walls and the paddle are appropriately handled.

4. **Block Destruction**
    - Blocks are destructible, and collisions between the ball and blocks trigger destruction.
    - Different block types are considered (normal, choco, star, heart).

5. **Bonus Block**
    - Bonus blocks (choco) appear randomly and provide additional scoring opportunities.

6. **Gold Ball Status**
    - Achieving a specific condition (e.g., hitting a star block) activates the gold ball status.
    - Gold ball status increases the ball speed and modifies the scoring.

7. **Level Progression**
    - The game progresses through levels upon completing the current level's objectives.

8. **Game Saving and Loading**
    - Players can save and load the game state.
    - Game state includes level, score, lives, and various flags.

9. **Scoring System**
    - Points are awarded for destroying blocks.
    - Bonus points are given for special conditions (e.g., hitting choco blocks).

10. **Game Over and Level Completion**
    - Game over conditions are appropriately handled, considering the player's lives.
    - Players advance to the next level upon completing the current level's objectives.

11. **Visual and UI Elements**
    - Visual elements, such as images and colors, enhance the game's appearance.
    - UI elements, including score and heart count, are displayed and updated.

## Additional Notes

Feel free to explore the codebase for more details on the implementation of these features. The game provides an engaging experience with a variety of interactive elements.

# Implemented but Not Working Properly

The following features have been implemented but are not working correctly in the Block Game application:

1. **Ball Freezing Issue**
    - **Issue**: The ball freezes spontaneously in random locations during gameplay.
    - **Steps Taken**: Investigated the code related to ball movement and collisions. Implemented additional logging to trace the issue. The cause is still under investigation.

2. **Background Change on Score 100**
    - **Issue**: The background doesn't change when the player's score reaches 100.
    - **Steps Taken**: Checked the logic responsible for background changes. Discovered an error in the score comparison condition. Modified the code to correctly trigger background changes on reaching a score of 100.

3. **Font Color Change on Background Change**
    - **Issue**: Font becomes hard to see when background changes.
    - **Steps Taken**: Examined the code handling background and font color changes. Identified a missing condition for font color updates. Added the necessary logic to change the font color to black when the background changes.

4. **Background Change After Level Completion**
    - **Issue**: The background does not change after completing a level.
    - **Steps Taken**: Reviewed the level completion logic and background change triggers. Identified a missing event hook for background updates after level completion. Implemented the missing logic to change the background after finishing a level.

5. **Ball Sticking to Paddle:** An attempt was made to code the ball to stick to the paddle every time it touched it. However, due to issues in calculating the ball's direction, this feature is not functioning correctly. The ball exhibits erratic behavior or freezes on the screen instead of sticking to the paddle.
    - **Issue**: The calculation of the ball's direction and movement lacked precision, leading to unexpected behaviors upon collision with the paddle.
    - **Impact**: The intended "stickiness" of the ball to the paddle was not achieved, affecting the overall gameplay experience.
    - **Steps Taken to Address**
      1. **Code Review:** Conducted a thorough review of the code related to ball-paddle interactions.
      2. **Debugging:** Implemented debugging techniques to trace the flow of execution during collisions.
      3. **Mathematical Corrections:** Adjusted calculations for updating the ball's position post-collision.
      4. **Testing:** Rigorously tested the modified code to validate changes and ensure desired behavior.
    - **Resolution**: The ball-paddle interaction issue was unsuccessfully addressed, and a rollback of the previous generation code was required to restore complete game functionality.
## Additional Notes

Ongoing efforts are being made to address the issues mentioned above. Developers are encouraged to collaborate on resolving these challenges and improving the overall functionality of the Block Game application.

# Features Not Implemented

### 1. **Advanced Ball Trajectory Prediction:**
- **Explanation:**
  Implementing an advanced ball trajectory prediction system was considered to enhance gameplay by providing players with more strategic control. However, the complexity involved in accurately predicting the ball's trajectory based on varying paddle angles, block configurations, and real-time physics proved to be a significant challenge.

- **Challenges Encountered:**
    - Predicting collisions with multiple blocks simultaneously.
    - Adjusting trajectory based on dynamic changes in paddle angle.
    - Balancing computational load to maintain smooth gameplay.

- **Reason for Omission:**
  The implementation complexity and potential performance impact outweighed the perceived gameplay benefits. Focusing on refining existing features and maintaining a consistent gaming experience took precedence over the challenges posed by this advanced trajectory prediction.

### 2. **Dynamic Level Generation:**
- **Explanation:**
  Dynamic level generation, where levels are procedurally generated based on algorithms, was envisioned to add variety to gameplay. However, developing a robust algorithm to create challenging yet balanced levels in real-time presented formidable challenges.

- **Challenges Encountered:**
    - Balancing difficulty without making levels too easy or overly complex.
    - Ensuring coherence and aesthetic appeal in dynamically generated levels.
    - Avoiding patterns that lead to unbeatable scenarios.

- **Reason for Omission:**
  The intricate nature of level design and the potential for unpredictable or unfair challenges in dynamically generated levels led to the decision to focus on handcrafted levels. This approach ensures a more curated and enjoyable gaming experience for players.

### 3. **Multiplayer Mode:**
- **Explanation:**
  Introducing a multiplayer mode for collaborative or competitive gameplay was contemplated to enhance the Block Game's social aspect. However, implementing real-time synchronization, handling network latency, and ensuring fair gameplay in a multiplayer environment posed substantial technical challenges.

- **Challenges Encountered:**
    - Synchronizing game state across multiple devices.
    - Implementing real-time ball and block interactions for all players.
    - Minimizing latency to maintain a seamless multiplayer experience.

- **Reason for Omission:**
  Given the scope and complexity of implementing a robust multiplayer mode, the decision was made to prioritize single-player gameplay. This choice allows for a more polished and stable gaming experience without compromising on quality.

## Conclusion
While these envisioned features could have added intriguing dimensions to the Block Game, practical considerations, development complexity, and the goal of delivering a stable and enjoyable user experience led to their exclusion from the current implementation.

# Modified Java Classes

## 1. **Main.java**
### Changes Made:
- **Added New Variable:**
    - Introduced a new boolean variable `isBallStuck` to track the ball's state, indicating whether it is stuck to the paddle or in motion.

- **Modified Ball Initialization:**
    - Modified the `initBall()` method to set the initial state of the ball as stuck to the paddle.

- **Enhanced Ball Physics:**
    - Extended the `setPhysicsToBall()` method to handle the case when the ball is stuck. Upon releasing, the ball's velocity and direction are adjusted based on the paddle's position.

- **Adjusted Paddle Movement:**
    - Modified the `move(int direction)` method to prevent paddle movement while the ball is stuck.

### Reasons for Modifications:
- **Ball Sticking Mechanism:**
    - The introduction of the `isBallStuck` variable and corresponding modifications in `initBall()` and `setPhysicsToBall()` were necessary to implement a controlled release of the ball from the paddle.

- **Enhanced Gameplay Dynamics:**
    - These changes aim to improve gameplay dynamics by providing more control to the player over the initial trajectory of the ball.

- **Paddle-Ball Interaction:**
    - Adjustments to the paddle movement prevent unintended interactions while the ball is in a stuck state, avoiding disruptions to the gaming experience.

## 2. **GameEngine.java**
### Changes Made:
- **Adjusted Physics Update Call:**
    - Extended the `onPhysicsUpdate()` method to include a call to `setPhysicsToBall()` from the `Main` class.

### Reasons for Modifications:
- **Integration with Main Class:**
    - The adjustment was made to ensure that ball physics updates, especially the `setPhysicsToBall()` method, are consistently applied during the physics update cycle in the game engine.

## Conclusion:
These modifications in the `Main` and `GameEngine` classes were essential for implementing a controlled ball release mechanism and ensuring smooth integration of ball physics within the game engine's update cycle.

# Unexpected Problems

## 1. **Issue: Ball Sticking to Paddle**
### Description:
- **Problem:**
    - The initial attempt to code the ball to stick to the paddle upon contact failed. The ball would freeze on the screen instead of adhering to the paddle.

- **Challenge:**
    - Difficulty in accurately calculating the direction and velocity of the ball when it contacts the paddle, leading to unexpected behavior.

- **Resolution Attempted:**
    - Various adjustments were made in the `setPhysicsToBall()` method to correctly handle the ball's behavior upon hitting the paddle.

- **Outcome:**
    - While some improvements were achieved, a comprehensive solution to ensure the ball consistently sticks to the paddle proved challenging. The issue remains partially addressed.

## 2. **Unexpected Rendering Glitches**
### Description:
- **Problem:**
    - Rendering glitches were observed during certain ball-paddle interactions, causing brief visual disruptions.

- **Challenge:**
    - Identifying the specific conditions triggering the rendering glitches and understanding the root cause.

- **Resolution Attempted:**
    - Debugging sessions were conducted to trace the sequence of events leading to rendering glitches. Adjustments in rendering calls and order were made to minimize the issue.

- **Outcome:**
    - While the frequency of rendering glitches was reduced, completely eliminating the issue required further investigation.

## Conclusion:
Unexpected challenges in achieving precise ball-paddle interactions and occasional rendering glitches posed difficulties during the assignment. Despite attempts to address these issues, complete resolution proved elusive, highlighting the complexity of fine-tuning game mechanics and graphics rendering.
