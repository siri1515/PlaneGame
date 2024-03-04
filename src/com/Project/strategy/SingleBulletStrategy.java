package com.Project.strategy;
import java.util.List;
import com.Project.entities.Bullet;

public class SingleBulletStrategy implements BulletStrategy {
    // The @Override annotation signifies that this method overrides a method from the implemented interface.
    @Override
    public void shoot(List<Bullet> bullets, int planeX, int planeY) {
        // Calculate the center position of the plane. Assuming the plane's width is 50 pixels.
        int planeCenterX = planeX + 50 / 2;
        // Adjust the starting x position of the bullet to be centered relative to the plane's position.
        // This calculation ensures the bullet starts from the middle of the plane.
        int bulletStartX = planeCenterX - Bullet.WIDTH;

        // Add a new bullet to the list of bullets with the calculated starting position.
        // The bullet will move up from this position.
        bullets.add(new Bullet(bulletStartX, planeY - Bullet.HEIGHT));
    }

    @Override
    public String toString() {
        return "Single Bullet Strategy";
    }
}

