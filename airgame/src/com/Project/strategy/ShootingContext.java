package com.Project.strategy;

import java.util.List; // Imports the List interface for handling collections of objects.
import javax.swing.JPanel; // Imports JPanel class for creating a panel within a GUI.
import com.Project.entities.Bullet;

// Declares the ShootingContext class, extending JPanel to incorporate GUI functionality.
public class ShootingContext extends JPanel {
    private BulletStrategy bulletStrategy; // A private field to hold the current BulletStrategy.

    // Constructor initializes the ShootingContext with a default SingleBulletStrategy.
    public ShootingContext() {
        this.bulletStrategy = new SingleBulletStrategy();
    }

    // Method to change the current BulletStrategy.
    public void setBulletStrategy(BulletStrategy strategy) {
        this.bulletStrategy = strategy;
		System.out.println("Bullet strategy changed to: " + strategy.toString());
    }

    // Executes the current BulletStrategy's shoot method, passing in the bullets list and plane coordinates.
    public void executeStrategy(List<Bullet> bullets, int planeX, int planeY) {
        bulletStrategy.shoot(bullets, planeX, planeY);
        System.out.println("Executing " + bulletStrategy.toString() + " at x: " + planeX + ", y: " + planeY);
    }

}


