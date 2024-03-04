package com.Project.strategy;
import java.util.List;
import com.Project.entities.Bullet;

// This class implements the BulletStrategy interface, defining a strategy for shooting two bullets simultaneously.
public class DoubleBulletStrategy implements BulletStrategy {
    // The @Override annotation indicates that this method is an implementation of a method declared in the BulletStrategy interface.
    @Override
    public void shoot(List<Bullet> bullets, int planeX, int planeY) {
        // Calculate the starting x position for the left bullet, adjusting so it fires from the left side of the plane.
        int leftBulletX = planeX - Bullet.WIDTH;
        // Calculate the starting x position for the right bullet, adjusting so it fires from the right side of the plane.
        // Assuming the plane's width is 50 pixels.
        int rightBulletX = planeX + 50 - Bullet.WIDTH;

        // Add two new bullets to the list: one starting from the left position, and the other from the right position.
        // Both bullets are positioned to start just above the plane, simulating a double-barreled gun effect.
        bullets.add(new Bullet(leftBulletX, planeY - Bullet.HEIGHT));
        bullets.add(new Bullet(rightBulletX, planeY - Bullet.HEIGHT));
    }

    @Override
    public String toString() {
        return "Double Bullet Strategy";
    }
}


