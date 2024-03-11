package com.Project.observer;

import com.Project.entities.Enemy;
//concrete subscribers
public class EnemyObserver implements PositionObserver
{
    
    @SuppressWarnings("unused")
    private Enemy enemy;

    public EnemyObserver(Enemy enemy)
    {
        this.enemy = enemy;
    }

    public void update(int x,int y)
    {
        System.out.println("Bullet hit at position:" + x + ", " + y);
    }
}
