package com.Project.entities;
import com.Project.observer.*;
import java.awt.Color;
import java.awt.Graphics;

// The ScoreboardUI class is responsible for displaying the game's scoreboard on the screen.
// It visually presents various game statistics like the total score and the count of enemies destroyed.
public class ScoreboardUI {
    // A reference to the ScoreManager object. The ScoreManager is part of the Observer pattern
    // and holds the game's scoring data. ScoreboardUI uses this data to display the score on screen.
    private ScoreManager scoreManager;

    // Constructor that initializes the ScoreboardUI with a ScoreManager.
    // scoreManager: The ScoreManager instance that contains the game's current score and enemy counts.
    public ScoreboardUI(ScoreManager scoreManager) {
        this.scoreManager = scoreManager; // Assigns the ScoreManager reference.
    }

    // The draw method is responsible for rendering the scoreboard on the game's UI.
    // g: The Graphics object used for drawing text and shapes on the screen.
    public void draw(Graphics g) {
        // Retrieves the current score and counts of different enemy types destroyed from the ScoreManager.
        int score = scoreManager.getScore();
        int normalEnemiesDestroyed = scoreManager.getNormalEnemiesDestroyed();
        int bossEnemiesDestroyed = scoreManager.getBossEnemiesDestroyed();

        // Sets the text color to white for visibility.
        g.setColor(Color.WHITE);

        // Draws the score and enemy count information at specific coordinates on the screen.
        g.drawString("Total Score: " + score, 10, 20); // Displays the total score.
        g.drawString("Normal Enemies Destroyed: " + normalEnemiesDestroyed, 10, 35); // Displays the count of normal enemies destroyed.
        g.drawString("Boss Enemies Destroyed: " + bossEnemiesDestroyed, 10, 50); // Displays the count of boss enemies destroyed.
    }
}


