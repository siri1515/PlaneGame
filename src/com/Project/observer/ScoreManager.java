package com.Project.observer;
import com.Project.factory.*;
import com.Project.entities.Enemy;

public class ScoreManager implements Observer {
    private int score = 0;
    private int normalEnemiesDestroyed = 0;
    private int bossEnemiesDestroyed = 0;

    @Override
    public void update(Enemy enemy) {
        if (enemy.getHealth() <= 0) {
            if (enemy instanceof NormalEnemy) {
                score += 4;
                normalEnemiesDestroyed++;
            } else if (enemy instanceof BossEnemy) {
                score += 10;
                bossEnemiesDestroyed++;
            }
            System.out.println("Score updated: " + score + ", Normal enemies destroyed: " + normalEnemiesDestroyed + ", Boss enemies destroyed: " + bossEnemiesDestroyed);
        }
    }

    public int getScore() {
        return score;
    }

    public int getNormalEnemiesDestroyed() {
        return normalEnemiesDestroyed;
    }

    public int getBossEnemiesDestroyed() {
        return bossEnemiesDestroyed;
    }
}

