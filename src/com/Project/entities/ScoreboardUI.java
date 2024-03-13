package com.Project.entities;
import com.Project.observer.*;
import java.awt.Color;
import java.awt.Graphics;

public class ScoreboardUI {
    private ScoreManager scoreManager;

    public ScoreboardUI(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public void draw(Graphics g) {
        int score = scoreManager.getScore();
        int normalEnemiesDestroyed = scoreManager.getNormalEnemiesDestroyed();
        int bossEnemiesDestroyed = scoreManager.getBossEnemiesDestroyed();

        g.setColor(Color.WHITE);
        g.drawString("Total Score: " + score, 10, 20);
        g.drawString("Normal Enemies Destroyed: " + normalEnemiesDestroyed, 10, 35);
        g.drawString("Boss Enemies Destroyed: " + bossEnemiesDestroyed, 10, 50);
    }
}

