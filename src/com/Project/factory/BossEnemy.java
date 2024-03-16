package com.Project.factory;

import com.Project.entities.Enemy;
import com.Project.flyweight.EnemyFlyweight;

// The BossEnemy class extends the abstract Enemy class, representing a "boss" type enemy.
// Boss enemies are typically stronger or more significant than normal enemies.
public class BossEnemy extends Enemy {
    // Constructor for creating a BossEnemy instance.
    // It adheres to the Flyweight pattern by accepting an EnemyFlyweight object as a parameter,
    // which contains shared attributes for this type of enemy (e.g., image, speed, width, height).
    // x, y: The initial position of the boss enemy on the game screen.
    // flyweight: An instance of EnemyFlyweight containing attributes shared among all boss enemies.
    public BossEnemy(int x, int y, EnemyFlyweight flyweight) {
        // Calls the superclass (Enemy) constructor, passing in the specific x, y coordinates,
        // a higher health value (10 in this case) indicative of its "boss" status, and the shared flyweight object.
        // The increased health value makes BossEnemy more durable compared to NormalEnemy instances,
        // requiring more effort from the player to defeat.
        super(x, y, 10, flyweight);
    }
}


