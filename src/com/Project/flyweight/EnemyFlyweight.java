package com.Project.flyweight;

import java.awt.Image;
import javax.swing.ImageIcon;

// The EnemyFlyweight class represents the shared state (intrinsic state) of enemies.
// It's part of the Flyweight design pattern, which is used to minimize memory usage by sharing as much data as possible.
public class EnemyFlyweight {
    // The image representing the visual appearance of the enemy.
    // This can be shared across many instances of enemies if they look the same.
    private Image image;

    // The speed of the enemy. This is considered as part of the flyweight because 
    // multiple enemies of the same type can share the same speed value.
    private int speed;

    // The width and height of the enemy. These dimensions are shared for enemies of the same type,
    // hence part of the flyweight to save memory.
    private int width, height;

    // Constructor to initialize the shared state of the enemy.
    // imagePath: Path to the image file used for the enemy's appearance.
    // speed: Common movement speed for this type of enemy.
    // width, height: Dimensions of the enemy, common for this type.
    public EnemyFlyweight(String imagePath, int speed, int width, int height) {
        this.image = new ImageIcon(imagePath).getImage(); // Loads the image from the provided path.
		this.speed = speed; // Sets the common speed.
        this.width = width; // Sets the common width.
        this.height = height; // Sets the common height.
    }

    // Getter for the image, allows accessing the visual representation of the enemy.
    public Image getImage() {
        return image;
    }

	// Getter for the speed, allows accessing the movement speed of the enemy.
	public int getSpeed() {
        return speed;
    }

    // Getter for the width, allows accessing the width of the enemy.
    public int getWidth() {
        return width;
    }

    // Getter for the height, allows accessing the height of the enemy.
    public int getHeight() {
        return height;
    }
}


