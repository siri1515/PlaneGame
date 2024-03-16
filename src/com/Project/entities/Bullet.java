package com.Project.entities;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

// The Bullet class represents the bullets fired by the player or enemies in the game.
public class Bullet {
    // Coordinates for the bullet's position. Private access modifier ensures encapsulation.
    private int x, y;

    // Constants defining the bullet's properties. 
    // SPEED: How fast the bullet moves.
    // WIDTH, HEIGHT: The dimensions of the bullet.
    // damage: The amount of damage this bullet inflicts on enemies.
    public static final int SPEED = 1;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;
    public static final int damage = 2;

    // The image representing the bullet. Loaded from resources.
    private Image image;

    // Constructor to initialize the bullet with its starting coordinates.
    // x, y: Initial position of the bullet.
    public Bullet(int x, int y) {
        this.x = x; // Assigns the x-coordinate.
        this.y = y; // Assigns the y-coordinate.
        // Loads the bullet image from the specified path.
        ImageIcon img = new ImageIcon(getClass().getResource("/images/bullet.png"));
        image = img.getImage(); // Retrieves the Image object from the ImageIcon.
    }

    // Moves the bullet by adjusting its y-coordinate.
    // The SPEED constant dictates how much the y-coordinate changes, simulating upward movement.
    public void move() {
        y -= SPEED;
    }

    // Draws the bullet on the game screen.
    // g: The Graphics object used for drawing.
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null); // Draws the bullet at its current coordinates.
    }

    // Checks if the bullet has moved off the top of the screen.
    // screenHeight: The height of the game screen.
    // Returns true if the bullet is above the top edge of the screen, indicating it's no longer visible.
    public boolean isOffScreen(int screenHeight) {
        return y < 0;
    }

    // Getter methods for the bullet's properties.
    // These methods provide read-only access to the bullet's coordinates, damage, width, and height.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
