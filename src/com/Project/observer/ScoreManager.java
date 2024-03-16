package com.Project.observer;
import com.Project.factory.*;
import com.Project.entities.Enemy;

// The ScoreManager class implements the Observer interface, making it capable of responding
// to changes in the state of observed subjects - in this case, enemies.
public class ScoreManager implements Observer {
    // score: Tracks the total score the player has earned.
    // normalEnemiesDestroyed: Counts the number of normal enemies the player has defeated.
    // bossEnemiesDestroyed: Counts the number of boss enemies the player has defeated.
    private int score = 0;
    private int normalEnemiesDestroyed = 0;
    private int bossEnemiesDestroyed = 0;

    // The update method is called whenever an observed enemy's state changes.
    // This method increments the player's score and counts of destroyed enemies based on the type of enemy.
    @Override
    public void update(Enemy enemy) {
        // Check if the enemy's health is 0 or less, indicating it has been destroyed.
        if (enemy.getHealth() <= 0) {
            // If the enemy is an instance of NormalEnemy, increase the score by 4
            // and increment the count of normal enemies destroyed.
            if (enemy instanceof NormalEnemy) {
                score += 4;
                normalEnemiesDestroyed++;
            // If the enemy is an instance of BossEnemy, increase the score by 10
            // and increment the count of boss enemies destroyed.
            } else if (enemy instanceof BossEnemy) {
                score += 10;
                bossEnemiesDestroyed++;
            }
            // Print the updated score and counts to the console.
            System.out.println("Score updated: " + score + ", Normal enemies destroyed: " + normalEnemiesDestroyed + ", Boss enemies destroyed: " + bossEnemiesDestroyed);
        }
    }

    // Getter methods for retrieving the current score and counts of destroyed enemies.
    // These methods provide read-only access to the private variables, encapsulating the state of the ScoreManager.
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


