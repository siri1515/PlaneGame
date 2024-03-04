package com.Project.entities;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bullet {
    // Private variables to hold the x and y coordinates of the bullet. These are accessible only within this class.
    private int x, y; 
    // Public static final variables defining the speed, width, and height of the bullet. These are constants and cannot be changed.
    public static final int SPEED = 1;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;
    private Image image; 

    // Constructor that initializes the bullet's position and image.
    public Bullet(int x, int y) {
        this.x = x; // Sets the x-coordinate.
        this.y = y; // Sets the y-coordinate.
        ImageIcon img = new ImageIcon(getClass().getResource("/images/bullet.png"));
        image = img.getImage(); // Gets the Image from the ImageIcon.
    }

    // Method to move the bullet. This is called to update the bullet's position.
    public void move() {
        y -= SPEED; // Moves the bullet up by decreasing its y-coordinate.
    }

    // Method to draw the bullet on the screen using the given Graphics object.
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null); // Draws the bullet image at its current position.
	}

    // Method to check if the bullet has moved off the screen.
    public boolean isOffScreen(int screenHeight) {
        return y < 0; // Returns true if the bullet's y-coordinate is less than 0, indicating it has moved off the top of the screen.
    }
}



