package com.Project.factory;

import com.Project.entities.Enemy;
import com.Project.flyweight.EnemyFlyweight;

// The NormalEnemy class is a concrete implementation of the abstract Enemy class.
// It represents a specific type of enemy within the game - the "normal" enemy.
public class NormalEnemy extends Enemy {
    // Constructor for creating a NormalEnemy instance.
    // It utilizes the Flyweight pattern by accepting an EnemyFlyweight object as a parameter,
    // which contains the shared state for this type of enemy (such as image, speed, width, and height).
    // x, y: The starting position of the normal enemy on the game screen.
    // flyweight: An instance of EnemyFlyweight containing the shared attributes for normal enemies.
    public NormalEnemy(int x, int y, EnemyFlyweight flyweight) {
        // Calls the superclass (Enemy) constructor, passing in the specific x, y coordinates,
        // a predefined health value (4 in this case), and the shared flyweight object.
        // The health value of 4 is specific to NormalEnemy instances, differentiating them
        // from other types of enemies in terms of durability.
		super(x, y, 4, flyweight);
	}
}


