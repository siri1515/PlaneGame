package com.Project.factory;

import com.Project.entities.Enemy;

//Enemy Type: boss
public class BossEnemy extends Enemy {
    public BossEnemy(int x, int y) {
        super(x, y, 1, 10, "resources/images/boss.png", 100, 100);
    }
}

