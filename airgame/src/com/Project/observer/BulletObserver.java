package com.Project.observer;

import com.Project.entities.Bullet;
//concrete subscribers
public class BulletObserver implements PositionObserver
{
    
    @SuppressWarnings("unused")
    private Bullet bullet;

    public BulletObserver(Bullet bullet)
    {
        this.bullet = bullet;
    }

    public void update(int x,int y)
    {
        System.out.println("Bullet hit at position:" + x + ", " + y);
    }



}
