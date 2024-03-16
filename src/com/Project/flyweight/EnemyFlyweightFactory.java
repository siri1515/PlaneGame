package com.Project.flyweight;

import java.util.HashMap;
import java.util.Map;

// The EnemyFlyweightFactory class is responsible for creating and managing flyweight objects.
// It serves as a central point for obtaining EnemyFlyweight instances, ensuring that identical 
// flyweights are shared and reused efficiently across the application.
public class EnemyFlyweightFactory {
    // A cache for storing and reusing flyweight objects. The key is a string representing the type of enemy
    // (e.g., "Normal", "Boss"), and the value is the corresponding EnemyFlyweight object.
    private Map<String, EnemyFlyweight> flyweights = new HashMap<>();

    // Retrieves an EnemyFlyweight object based on the type of enemy.
    // If a flyweight for the specified type doesn't exist yet, it is created and stored in the map for future use.
    public EnemyFlyweight getFlyweight(String type) {
        // Checks if the flyweight object for the given type already exists in the cache.
        if (!flyweights.containsKey(type)) {
            // If the flyweight does not exist, create a new one based on the type and add it to the cache.
            switch (type) {
                // Case for creating a "Normal" enemy flyweight with specified attributes.
                case "Normal":
                    flyweights.put(type, new EnemyFlyweight("resources/images/normal.png", 2, 50, 50));
                    break;
                // Case for creating a "Boss" enemy flyweight with specified attributes.
                case "Boss":
                    flyweights.put(type, new EnemyFlyweight("resources/images/boss.png", 1, 100, 100));
                    break;
            }
        }
        // Returns the existing or newly created flyweight object from the cache.
        return flyweights.get(type);
    }
}

