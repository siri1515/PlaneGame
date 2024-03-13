package com.Project.factory;

import com.Project.entities.Enemy;

//Enemy Type: normal
public class NormalEnemy extends Enemy {
    public NormalEnemy(int x, int y) {
        super(x, y, 2, 4, "resources/images/normal.png", 50, 50);
    }
}

