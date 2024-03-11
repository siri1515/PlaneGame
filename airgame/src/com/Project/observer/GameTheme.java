package com.Project.observer;

import java.util.ArrayList;
import java.util.List;
//Publisher
public class GameTheme
{
    private List<PositionObserver> observers = new ArrayList<>();

   

    public void addObserver(PositionObserver observer) 
    {
        observers.add(observer);
    }

    public void removeObserver(PositionObserver observer)
    {
        observers.remove(observer);
    }

    public void updateBulletPosition(int x, int y) 
    {
        
        notifyObservers(x,y);
    }

    public void updateEnemyPosition(int x, int y)
    {
        
        notifyObservers(x,y);
    }

    private void notifyObservers(int x, int y)
    {
        for(PositionObserver observer : observers)
        {

        observer.update(x,y);
        }
    }
}