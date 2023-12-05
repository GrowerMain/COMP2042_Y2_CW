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
- Press 'C' to use special move in the game.
- Press 'W' to skip a level in the game.
- Press 'ESC' to instantly restart the game.



# Implemented and Functional Features

The following features have been successfully implemented and are functioning as expected in the Block Game application:

- **Start of level**
  - The ball will not bounce straight away when the next level starts, and the user will have the press "SPACE" in order to release the ball.
  - This will give more control to the user on where the ball is launched from and prevent a loss of live if the balls spawns in an unfavourable position.

- **Restart Game**
  - The game will now completely reset if the 'ESC' key is pressed to allow for rapid resets of the board at the start of the game
  - This would improve the quality of life for speed runners of the game

- **Respawn Ball**
  - The Ball will despawn when it hits the bottom border of the game and a new one will spawn on top of the paddle
  - The player will be able to control the paddle and choose where to release the ball by pressing "SPACE"
  - This allows for more control of the ball direction to the user in between lives

- **Gold Ball extra Perks**
  - GoldBall status now gives the user and extra 3 multiplier to points awarded for destroying blocks.
  - Every time a goldBall block is destroyed the player will gain 1 extra special move

- **Special move**
  - The ball will now inverse its direction if the 'C' key is pressed, with a bump in the speed
  - This will allow players more than one way to manipulate the direction of the ball
  - Allowing for more high skilled players to aim for challenges like not touching the paddle at all in a level

- **Heart block update**
  - Every time a Heart block is destroyed the player will gain an extra 5 special move

- **Level Progression Prompt**
  - There will be a prompt to the user to indicate that the level is complete.

- **Extra Block layers**
  - Each level has been modified to have 3 extra layers of blocks for more gameplay and a better experience.

- **Game Victory**
  - Players advance to the next level upon completing the current level's objectives.
  - Once player has completed a certain number of levels the game will end and the player has won
  - The players score will also be displayed in the prompt.

- **Game Over**
  - Game over conditions are appropriately handled, considering the player's lives.
  - There will be a prompt to the user to indicate that the game is over.

- **Game Saving and Loading**
  - Players can save and load the game state.
  - Game state includes level, score, lives, and various flags.
  - There will be a prompt to the user to indicate that the game has been saved.

- **Visual and UI Elements**
  - Visual elements, such as images and colors, enhance the game's appearance.
  - UI elements, including score and heart count, are displayed and updated.

- **Background Change After Level Completion**
  - The player will experience different backgrounds and dialogs to increase tension and player engagement throughout the play through for the brick game
  - Each level has a pre-determined background for story telling purpose
  - The player will have to ascend through each level to find out what happens next and discover new backgrounds.
  - If the player would like to just switch backgrounds and go through the story line without playing the game, they can press 'W' to skip the gameplay.

- **Storyline**
  - A dialog box will appear at the start of each level
  - The player will be spoken to in first person
  - There will be a campaign style storyline attached to the gamplay
  - Unlock new dialogs as you level up!
  - Players start every new game with an amount of 5 usages of the special move, with ways to earn more during gameplay

- **Sounds**
  - The game now has sounds that are saved within the local project folder.
  - They are set up to be dynamically retrievable from any different root source path folders
  - The sounds are only played on special events that will trigger upon specific player actions

- **Sound for Destroying special blocks**
  - Every time a special block is destroyed a unique wound will be played for better player engagement.

- **Sound for level-up and game start**
  - The game will play a sound whenever there is a level up event or a game start event.

# Implemented but Inoperative Features

The following features have been implemented but are not working correctly in the Block Game application:

1. **Ball Freezing Issue**
   - **Issue**: The ball freezes spontaneously in random locations during gameplay.
   - **Steps Taken**: Investigated the code related to ball movement and collisions. Implemented additional logging to trace the issue. Code was rolled back to the previous version die to not being able to find the cause.

2. **Font Color Change on Background Change**
   - **Issue**: Font becomes hard to see when background changes to a low contrast color.
   - **Steps Taken**: Examined the code handling background and font color changes. Identified a missing condition for font color updates. Added the necessary logic to change the font color to black when the background changes.
   - **Resolution**:
     - The font color issue was unsuccessfully addressed on a coding level, as different backgrounds will require a different color for most contrast and visibility
     - All the backgrounds chosen for the final version either had a dark color theme or have a darker color area where all the text are displayed.
     - As a result, the players readability of the text is not affected much throughout all the game levels.

3. **Ball Sticking to Paddle:** An attempt was made to code the ball to stick to the paddle at higher levels of the game every time it touched it. However, due to issues in calculating the ball's direction, this feature is not functioning correctly. The ball exhibits erratic behavior or freezes on the screen instead of sticking to the paddle.
   - **Issue**: The game would freeze upon collision with the paddle a second time.
   - **Impact**: The intended "stickiness" of the ball to the paddle was not achieved, affecting the overall gameplay experience.
   - **Steps Taken to Address**
     1. **Code Review:** Conducted a thorough review of the code related to ball-paddle interactions.
     2. **Debugging:** Implemented debugging techniques to trace the flow of execution during collisions.
   - **Resolution**: The ball-paddle interaction issue was unsuccessfully addressed, and a rollback of the previous generation code done. The ball now only sticks to the paddle at each start of a new level.

# Unimplemented Features

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
  - Generation of blocks in unreachable areas that would cause the player to reach a deadlock scenario where the player will never be able to win.

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

# New Java Classes

## SoundPlayer Class

The `SoundPlayer` class is designed for handling audio playback in a Block application. It utilizes the JavaFX media framework to manage the loading and playing of audio files. The class includes the following methods:

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
- **Added `isBallStuck` Variable:**
    - Introduced a new boolean variable `isBallStuck` to track the ball's state, indicating whether it is stuck to the paddle or in motion.

- **Modified `initBall()`:**
    - Modified the `initBall()` method to set the initial state of the ball as stuck to the paddle.

- **Modified `setPhysicsToBall()`:**
    - Extended the `setPhysicsToBall()` method to handle the case when the ball is stuck. Upon releasing, the ball's velocity and direction are adjusted based on the paddle's position.

- **Modified `move(int direction)`:**
    - Modified the `move(int direction)` method to prevent paddle movement while the ball is stuck.
  
- **Added new method `updateBackground`:**
    - Changes the background every level to co-inside with the newly developed campaign story of the game

- **Modified `start()`:**
        - Included dialog messages that load in at the start of each level, that provides a vivid and interesting storyline for users to follow as the play the game.

### Reasons for Modifications:

- **`isBallStuck`:**
    - The introduction of the `isBallStuck` variable and corresponding modifications in `initBall()` and `setPhysicsToBall()` were necessary to implement a controlled release of the ball from the paddle.

- **`initBall()`:**
  - These changes aim to improve gameplay dynamics by providing more control to the player over the initial trajectory of the ball.

- **`setPhysicsToBall()`:**
  - Adjustments to the paddle movement prevent unintended interactions while the ball is in a stuck state, avoiding disruptions to the gaming experience.
- **`move(int direction)`:**
    -Extracted the code of the move method into a different class for better code readability and apply the principles of the SOLID programming standard.

- **Added new method `updateBackground`:**
  - The method allows for plug and play of different background photos easily, making it easier for a developer to swap out the given set of photos, and have different sequence or photos displayed entirely.
  
- **Modified `start()`:**
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
    - The added functions `showMessage(String message, final Main main)` provide better user interaction by displaying informative messages during key events in the game.

- **Improved End-Game Experience:**
    - The `showGameOver` and `showWin` functions enhance the end-game experience by presenting clear messages to the player.

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

## 2. **Integration of JavaFX into the project**
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
  - All the javaFX imports was able to resolve and the game could successfully compile and run, for the first time.

## 3. **Playing of sounds during gameplay**
### Description:
- **Problem:**
  - The initial attempt to add sounds to the game was met with the issue of the sound events only triggering once, and after that the sound would not play anymore although the requirements was met.

- **Challenge:**
  - Identifying the specific conditions triggering the sound glitches and understanding the root cause.

- **Resolution Attempted:**
  - Debugging sessions were conducted to trace the sequence of events leading to sound glitches.
  - The issue is related to the fact that the MediaPlayer function has a state, and once it's in a terminal state (like STOPPED or FINISHED), it cannot be replayed without going back to a non-terminal state.
  - To address this issue, the MediaPlayer function is reset to its initial state before playing it again.
- **Outcome:**
  - The sound now works like intended and can be played repeatedly without issue.


# Additional Notes for General Troubleshooting

- This application uses JavaFX for the GUI. Ensure that you have the JavaFX SDK correctly configured.

- If you encounter any issues or errors, please check the console output for error messages and ensure that all dependencies are correctly set up.

# Conclusion and Summary
The development of the Block Game application has been a challenging yet rewarding journey in demonstrating the principles of developing maintainable software. Despite encountering unexpected issues and unimplemented features, significant progress has been made in enhancing the gaming experience through implemented and functional features.

The inclusion of advanced gameplay elements such as dynamic backgrounds, a captivating storyline, and a sound system adds depth and engagement to the Block Game. The introduction of a SoundPlayer class and a Direction enum contributes to better code organization and readability.

Several modifications to the existing Java classes, particularly in Main, GameEngine, and Score, aim to improve user interaction, provide a more controlled release of the ball, and enhance the overall gaming experience. The introduction of dialog prompts, background updates, and additional layers of blocks further enriches the gameplay.

While unexpected challenges, such as the ball sticking to the paddle issue, have been partially addressed, the commitment to delivering a stable and enjoyable user experience remains evident. The decision to omit certain features, like advanced ball trajectory prediction lines and multiplayer, reflects a strategic focus on maintaining a curated and balanced gameplay experience.

Troubleshooting efforts, including the transition to IntelliJ and resolving JavaFX import issues, have contributed to a more streamlined development process. The successful implementation of sound events during gameplay is a testament to the dedication to resolving unforeseen challenges.

In summary, the Block Game application showcases not only the functionality of a game but also the importance of maintaining code quality, addressing unexpected issues, and prioritizing user experience. The journey from unexpected problems to resolution has been a valuable learning experience, emphasizing the complexities and nuances of software development.