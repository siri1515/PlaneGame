package com.Project.factory;

import com.Project.entities.Enemy;

public class EnemyFactory {
    public static Enemy createEnemy(String type, int x, int y) {
        switch (type) {
            case "Normal":
                return new NormalEnemy(x, y);
            case "Boss":
                return new BossEnemy(x, y);
            default:
                return null;
        }
    }
}


