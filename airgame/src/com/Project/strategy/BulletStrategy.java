package com.Project.strategy;
import com.Project.entities.Bullet;
import java.util.List;

public interface BulletStrategy {
    // Abstract method shoot that takes a list of Bullets and coordinates (planeX, planeY) as parameters.
    // Classes that implement this interface must provide an implementation for this method.
    void shoot(List<Bullet> bullets, int planeX, int planeY);
    // The method is expected to handle the logic for shooting bullets. This could involve adding new Bullet
    // objects to the list based on the current position of the plane represented by planeX and planeY.
	@Override
    String toString(); 
}
