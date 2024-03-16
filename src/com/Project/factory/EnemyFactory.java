package com.Project.factory;

import com.Project.entities.Enemy;
import com.Project.flyweight.*;

// The EnemyFactory class is responsible for creating Enemy objects.
// It implements the Factory Design Pattern to encapsulate the instantiation logic of Enemy objects.
public class EnemyFactory {
    // An instance of EnemyFlyweightFactory, used to obtain flyweight objects that contain shared state.
    // This is part of the Flyweight Design Pattern, optimizing memory usage by reusing common state among enemies.
    private EnemyFlyweightFactory flyweightFactory;

    // Constructor initializes the factory with an instance of EnemyFlyweightFactory.
    // This allows the EnemyFactory to access pre-existing flyweight objects for enemy creation.
    public EnemyFactory(EnemyFlyweightFactory flyweightFactory) {
        this.flyweightFactory = flyweightFactory;
    }

    // createEnemy method creates enemies of a specified type (e.g., "Normal" or "Boss") at given coordinates.
    // This method demonstrates the use of both Factory and Flyweight patterns.
    public Enemy createEnemy(String type, int x, int y) {
        // Obtains a flyweight object from the flyweightFactory. The flyweight object contains shared state
        // such as the image, speed, width, and height of the enemy, based on its type.
        EnemyFlyweight flyweight = flyweightFactory.getFlyweight(type);

        // Initializes the enemy variable to null. It will be assigned to a specific enemy instance.
        Enemy enemy = null;
        
        // Switch statement determines the type of enemy to be created.
        // It uses the shared flyweight to instantiate specific types of enemies.
        switch (type) {
            case "Normal":
                // Creates a NormalEnemy with its unique position and shared flyweight state.
                enemy = new NormalEnemy(x, y, flyweight);
                break;
            case "Boss":
                // Creates a BossEnemy with its unique position and shared flyweight state.
                enemy = new BossEnemy(x, y, flyweight);
                break;
        }
        
        // Returns the newly created Enemy object, which could be a NormalEnemy, BossEnemy, or null if the type does not match.
        return enemy;
    }
}
