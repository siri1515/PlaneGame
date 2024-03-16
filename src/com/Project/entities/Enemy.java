package com.Project.entities;
import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.Project.flyweight.EnemyFlyweight;
import com.Project.observer.*;

// The abstract Enemy class represents the common characteristics and behaviors of enemies in the game.
// This class implements the Event interface to allow for the Observer pattern implementation.
public abstract class Enemy implements Event {
    // X and Y coordinates for the enemy's position on the screen.
    protected int x, y;
	
	// The EnemyFlyweight object that contains shared state amongst similar types of enemies.
    // This is part of the Flyweight pattern to save memory by sharing common data.
	protected EnemyFlyweight flyweight;

    // Current health of the enemy. Individual for each enemy, not part of the Flyweight.
    protected int health;

    // A list to hold all observers that are observing this enemy.
    // Part of the Observer design pattern to allow for notification of state changes.
	private List<Observer> observers = new ArrayList<>();

    // Constructor initializes an enemy with its unique state (x, y, health) and its shared state (flyweight).
	public Enemy(int x, int y, int health, EnemyFlyweight flyweight) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.flyweight = flyweight;
    }

    // Getters for the position coordinates.
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Moves the enemy down the screen using the speed defined in the shared flyweight.
    public void move() {
        y += flyweight.getSpeed();
    }

    // Draws the enemy on the screen using its current position and the image from the shared flyweight.
	public void draw(Graphics g) {
        g.drawImage(flyweight.getImage(), x, y, flyweight.getWidth(), flyweight.getHeight(), null);
    }

    // Checks if the enemy has passed beyond the bottom edge of the screen.
    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight;
    }

	// Checks if the enemy has been hit by a bullet using simple collision detection.
	public boolean isHit(Bullet bullet) {
        int bulletX = bullet.getX();
        int bulletY = bullet.getY();
        int bulletWidth = bullet.getWidth();
        int bulletHeight = bullet.getHeight();

        boolean hit = bulletX < x + flyweight.getWidth() && bulletX + bulletWidth > x &&
                      bulletY < y + flyweight.getHeight() && bulletY + bulletHeight > y;
        return hit;
    }

    // Reduces the enemy's health by a specified amount and notifies observers if health drops to 0 or below.
	public void reduceHealth(int damage) {
		this.health -= damage;
		if (this.health <= 0) {
            notifyObservers();
        }
    }

    // Getter for the enemy's health.
	public int getHealth() {
        return health;
    }

    // Registers an observer to this enemy.
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    // Removes an observer from this enemy.
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notifies all registered observers of a state change.
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
