# COMP2042_CW_hfynl1
Coursework for Developing Maintainable Software

# Block Game Application

This is a Block Game application built with JavaFX to demonstrate the implementation of developing maintainable software.

## Prerequisites

Before you compile and run the application, make sure you have the following installed:

1. **Java Development Kit (JDK):**

   Download and install the latest version of JDK from the official Oracle website: [Java SE Downloads](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **JavaFX SDK:**

   JavaFX is bundled with JDK 8 and later versions. No separate installation is required.

3. **GitHub Desktop:**

   Download and install GitHub Desktop from the official Git website: [GitHub Downloads](https://desktop.github.com/)

4. **Git:**

   Download and install Git from the official Git website: [Git Downloads](https://git-scm.com/downloads)

5. **IntelliJ IDEA Community Edition:**

   Download and install IntelliJ IDEA Community Edition, a free and open-source version of IntelliJ IDEA:

    - Download: [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/)

## Opening the Project in IntelliJ IDEA

1. **Clone the repository:**

    ```bash
        git clone https://github.com/your-username/COMP2042_CW_hfynl1
        cd COMP2042_CW_hfynl1
    ```
   Alternatively you can log into the GitHub Desktop and login to your account, click on clone repositories > search for "COMP2042_CW_hfynl1", select it and clone it into your computer. 

2. **Open the project in IntelliJ IDEA:**

    - Open IntelliJ IDEA.
    - Choose `Open` from the main menu.
    - Navigate to the `COMP2042_CW_hfynl1` directory and select it.

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
- Press the space key to release the ball at the start of each new level.
- Press 'S' to save the game.
- Press 'ESC' to instantly restart the game.

## Additional Notes

- This application uses JavaFX for the GUI. Ensure that you have the JavaFX SDK correctly configured.

- If you encounter any issues or errors, please check the console output for error messages and ensure that all dependencies are correctly set up.

# Implemented Features

The following features have been successfully implemented and are functioning as expected in the Block Game application:

1. **Restart Game**
    - The game will now complete reset if the 'ESC' key is pressed to allow for rapid resets of the board at the start of the game
    - This would improve the quality fo life for speed runners of the game

2. **Start of level tweak**
    - The ball will not bounce straight away when the next level starts, and the user will have the press "SPACE" in order to release the ball and begin the level.
    - This will give more control to the user on where they would like to begin playing the game from and make it more engaging for the player.

3. **Gold Ball extra Perks**
    - GoldBall status now gives the user and extra 3 multiplier to points awarded for destroying blocks.

4. **Wallpaper change**
    - The wallpaper now changes according to your score, for a more interactive and engaging playing experience.

5. **Extra Block layers**
    - Each level has been modified to have 3 extra layers of blocks for more gameplay and a better experience.
   
6. **Level Progression**
    - The game progresses through levels upon completing the current level's objectives.
    - There will be a prompt to the user to indicate that the level is complete.

7. **Game Saving and Loading**
   - Players can save and load the game state.
   - Game state includes level, score, lives, and various flags.
   - There will be a prompt to the user to indicate that the game has been saved.

8. **Game Over**
     - Game over conditions are appropriately handled, considering the player's lives.
     - There will be a prompt to the user to indicate that the game is over.
   
9. **Game Victory**
   - Players advance to the next level upon completing the current level's objectives.
   - Once player has completed a certain number of levels the game will end and the player has won
   - The players score will also be displayed in the prompt.

10. **Visual and UI Elements**
    - Visual elements, such as images and colors, enhance the game's appearance.
    - UI elements, including score and heart count, are displayed and updated.

11. **Background Change After Level Completion**
    - The player will experience different backgrounds and dialogs to increase tension and player engagement throughout the play through for the brick game
    - Each level has a pre-determined background for story telling purpose
    - The player will have to ascend through each level to find out what happens next and discover new backgrounds.
    - If the player would like to just switch backgrounds and go through the story line without playing the game, they can press 'W' to skip the gameplay.
12. **Storyline**
    - A dialog box will appear at the start of each level
    - The player will be spoken to in first person
    - There will be a campaign style storyline attached to the gamplay
    - Unlock new dialogs as you level up!
13. **Respawn Ball**
    - The Ball will despawn when it hits the bottom border of the game and a new one will spawn on top of the paddle
    - The player will be able to control the paddle and choose where to release the ball by pressing "SPACE"
    - This allows for more control of the ball direction to the user in between lives
14. **Special move**
    - The ball will now inverse its direction if the 'C' key is pressed, with a bump in the speed for a swift 180 turn
    - This will allow players more than one way to manipulate the direction of the ball
    - Allowing for more high skilled players to aim for challenges like not touching the paddle at all in a level
    - players start every new game with a default amount of 5 uses of the special move, with ways to earn more during gameplay
15. **Heart block update**
    - Every time a Heart block is destroyed the player will gain an extra 5 special move
16. **goldBall block update**
    - Every time a goldBall block is destroyed the player will gain 1 extra special move 
17. **Sound for Destroying special blocks**
    - Every time a special block is destroyed a unique wound will be played for better player engagement.
18. **Sound for level-up and game start**
    - The game will play a sound whenever there is a level up event or a game start event.
19. **Sounds**
    - The game now has sounds that are save within the local project folder.
    - They are set up to be dynamically retrievable from any different root source path folders
    - The sounds are only played on special events that will trigger upon specific player actions

## Additional Notes

Feel free to explore the codebase for more details on the implementation of these features. The game provides an engaging experience with a variety of interactive elements.

# Implemented but Not Working Properly

The following features have been implemented but are not working correctly in the Block Game application:

1. **Ball Freezing Issue**
    - **Issue**: The ball freezes spontaneously in random locations during gameplay.
    - **Steps Taken**: Investigated the code related to ball movement and collisions. Implemented additional logging to trace the issue. The cause is still under investigation.

2. **Font Color Change on Background Change**
    - **Issue**: Font becomes hard to see when background changes.
    - **Steps Taken**: Examined the code handling background and font color changes. Identified a missing condition for font color updates. Added the necessary logic to change the font color to black when the background changes.
    - **Resolution**:
      - The font color issue was unsuccessfully addressed, as different backgrounds will require a different color for most contrast and visibility
      - A rollback of the previous generation code was required to restore complete game functionality.
      - All the backgrounds chosen either had a dark color theme or have a darker color area where all the text are displayed.
      - As a result, the players readability of the text is not affected much throughout all the game levels.

3. **Ball Sticking to Paddle:** An attempt was made to code the ball to stick to the paddle at higher levels of the game every time it touched it. However, due to issues in calculating the ball's direction, this feature is not functioning correctly. The ball exhibits erratic behavior or freezes on the screen instead of sticking to the paddle.
    - **Issue**: The calculation of the ball's direction and movement lacked precision, leading to unexpected behaviors upon collision with the paddle.
    - **Impact**: The intended "stickiness" of the ball to the paddle was not achieved, affecting the overall gameplay experience.
    - **Steps Taken to Address**
      1. **Code Review:** Conducted a thorough review of the code related to ball-paddle interactions.
      2. **Debugging:** Implemented debugging techniques to trace the flow of execution during collisions.
      3. **Testing:** Rigorously tested the modified code to validate changes and ensure desired behavior.
   - **Resolution**: The ball-paddle interaction issue was unsuccessfully addressed, and a rollback of the previous generation code was required to restore complete game functionality.
## Additional Notes

Ongoing efforts are being made to address the issues mentioned above. Developers are encouraged to collaborate on resolving these challenges and improving the overall functionality of the Block Game application.

# Features Not Implemented

### 1. **Advanced Ball Trajectory Prediction Lines:**
- **Explanation:**
  Implementing an advanced ball trajectory prediction system was considered to enhance gameplay by providing players with more strategic control. However, the complexity involved in accurately drawing the ball's trajectory line based on varying paddle angles, block configurations, and real-time physics proved to be a significant challenge.

- **Challenges Encountered:**
    - Predicting collisions with multiple blocks simultaneously.
    - Adjusting trajectory based on dynamic changes in paddle angle.
    - Not knowing when to stop displaying the prediction lines as it would be useless to the player and would only be a distraction to the gameplay.
    - Balancing computational load to maintain smooth gameplay.

- **Reason for Omission:**
  The implementation complexity and potential performance impact outweighed the perceived gameplay benefits. Focusing on refining existing features and maintaining a consistent gaming experience took precedence over the challenges posed by this advanced trajectory prediction line.

### 2. **Dynamic Level Generation:**
- **Explanation:**
  Dynamic level generation, where blocks are procedurally generated based on algorithms mid-level, was envisioned to add variety to gameplay. However, developing a robust algorithm to create challenging yet balanced levels in real-time presented formidable challenges.

- **Challenges Encountered:**
    - Balancing difficulty without making levels too easy or overly complex.
    - Ensuring coherence and aesthetic appeal in dynamically generated levels.
    - Avoiding patterns that lead to unbeatable scenarios.
    - Generation of blocks in unreachable areas that would cause the level to reach a deadlock scenario where the player will never be able to win.

- **Reason for Omission:**
  The intricate nature of level design and the potential for unpredictable or unfair challenges in dynamically generated levels led to the decision to focus on statically generated blocks pre-levels. This approach ensures a more curated and enjoyable gaming experience for players.

### 3. **Multiplayer Mode:**
- **Explanation:**
  Introducing a multiplayer mode for collaborative or competitive gameplay was contemplated to enhance the Block Game's social aspect. However, implementing real-time synchronization, handling network latency, and ensuring fair gameplay in a multiplayer environment posed substantial technical challenges.

- **Challenges Encountered:**
    - Synchronizing game state across multiple devices.
    - Implementing real-time ball and block interactions for all players.
    - Minimizing latency to maintain a seamless multiplayer experience.

- **Reason for Omission:**
  Given the scope and complexity of implementing a robust multiplayer mode, the decision was made to prioritize single-player gameplay. This choice allows for a more polished and stable gaming experience without compromising on quality.

### 4. **Moving background:**
- **Explanation:**
  Introducing a moving background would make gameplay more engaging and improve player overall experience

- **Challenges Encountered:**
    - Having a background constantly moving can be confusing to focus on the ball.
    - Moving background might cause user confusion between blocks and the background, potentially being a distraction for the player
    - Rendering of the background can cause the game to lag on weaker devices
    - the low contrast of colors between the background and blocks might affect overall gameplay

- **Reason for Omission:**
  Given the scope and complexity of implementing a robust manimated background, the decision was made to prioritize static background gameplay. This choice allows for a more polished and stable gaming experience without compromising on quality.

## Conclusion
While these envisioned features could have added intriguing dimensions to the Block Game, practical considerations, development complexity, and the goal of delivering a stable and enjoyable user experience led to their exclusion from the current implementation.

# New Java Classes

## SoundPlayer Class

The `SoundPlayer` class is designed for handling audio playback in a JavaFX application. It utilizes the JavaFX media framework to manage the loading and playing of audio files. The class includes the following methods:

### Constructor

- `public SoundPlayer(String soundFilePath)`: Initializes a `SoundPlayer` object with the path to an audio file.
    - **Parameters:**
        - `soundFilePath`: A `String` representing the file path of the audio file.

### play() Method

- `public void play()`: Plays the loaded sound. This method stops the current playback, resets the media player to its initial state, and then starts playing the sound.

### reset() Method

- `public void reset()`: Stops the current playback and resets the media player to its initial state.

## Direction Enum

The `Direction` enum represents the possible movement directions in a game. It includes two constants: `LEFT` and `RIGHT`, which signify leftward and rightward movements, respectively.

### Constants

- `LEFT`: Represents the left direction.
- `RIGHT`: Represents the right direction.

### Usage Example

The `Direction` enum provides a convenient and type-safe way to handle movement directions in a game. Usage examples include:

- ```java
  Direction playerDirection = Direction.LEFT;
  // Perform actions for left movement

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
  
- **Added new method updateBackground:**
    - Changes the background every level to co-inside with the newly developed campaign story of the game
- **Modified start():**
    - Included dialog messages that load in at the start of each level, that provides a vivid and interesting storyline for users to follow as the play the game.

### Reasons for Modifications:
- **Ball Sticking Mechanism:**
  - The introduction of the `isBallStuck` variable and corresponding modifications in `initBall()` and `setPhysicsToBall()` were necessary to implement a controlled release of the ball from the paddle.

- **Enhanced Gameplay Dynamics:**
  - These changes aim to improve gameplay dynamics by providing more control to the player over the initial trajectory of the ball.

- **Paddle-Ball Interaction:**
  - Adjustments to the paddle movement prevent unintended interactions while the ball is in a stuck state, avoiding disruptions to the gaming experience.
- **Added new method updateBackground:**
  - The method allows for plug and play of different background photos easily, making it easier for a developer to swap out the given set of photos, and have different sequence or photos displayed entirely.
  
- **Modified start():**
  - The method allows for plug and play of different dialogs easily, making it easier for a developer to swap out the given set of dialogs, and create a different storyline altogether.

## 2. **GameEngine.java**
### Changes Made:
- **Adjusted Physics Update Call:**
      - Extended the `onPhysicsUpdate()` method to include a call to `setPhysicsToBall()` from the `Main` class.

### Reasons for Modifications:
- **Integration with Main Class:**
    - The adjustment was made to ensure that ball physics updates, especially the `setPhysicsToBall()` method, are consistently applied during the physics update cycle in the game engine.

## 3. **Score.java**
### Changes Made:
- **Added Pop-Up Message Function:**
    - Implemented a function `showMessage(String message, final Main main)` to display pop-up messages.

- **Added Game Over Display:**
    - Implemented a function `showGameOver(final Stage primaryStage)` to display a game over message with a restart button.

- **Added Win Display:**
    - Implemented a function `showWin(final Stage main)` to display a win message upon reaching a specific level.

### Reasons for Modifications:
- **Enhanced User Interaction:**
    - The added functions provide better user interaction by displaying informative messages during key events in the game.

- **Improved End-Game Experience:**
    - The `showGameOver` and `showWin` functions enhance the end-game experience by presenting clear messages to the player.

## Conclusion:
These modifications in the `Main`, `GameEngine`, and `Score` classes contribute to a more engaging and informative user experience in the Block Game application.

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
## 3. **Integration of JavaFX into the project**
### Description:
- **Problem:**
    - The initial attempt to resolve javaFX imports took multiple hours of online forum searching and downloading tons of libraries locally, all in the attempt to get the game to compile

- **Challenge:**
  - Initially I was using visual studio code to edit the project folder, but VSC was not built specially to process java projects so there was alot of steps that are automated in other apps like Eclispe and Intellij that I have to do manually.


- **Resolution Attempted:**
  - After an extensive two-week troubleshooting process and thorough online research, I decided to transition from Visual Studio to IntelliJ due to unresolved issues.
  - To start afresh, I deleted the GitHub repository and uploaded a new copy of the original file.
  - Upon switching to IntelliJ, I cloned the repository and opened the folder, where the software promptly detected an issue. A quick fix prompt provided a resolution by automatically updating the dependencies in the pom.xml file.
  - The transition and issue resolution in IntelliJ were notably efficient, requiring a fraction of the time compared to the prolonged troubleshooting with Visual Studio.

- **Outcome:**
  - All the javaFX imports was able to resolve and I could successfully compile the project into a playable game, for the first time.


## Conclusion:
Unexpected challenges in achieving precise ball-paddle interactions and occasional rendering glitches posed difficulties during the assignment. Despite attempts to address these issues, complete resolution proved elusive, highlighting the complexity of fine-tuning game mechanics and graphics rendering.
