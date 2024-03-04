package com.Project.entities;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class Enemy {
    // Position of the enemy on the game screen
    protected int x, y;
    // Speed at which the enemy moves
    protected int speed;
    // Health points of the enemy
    protected int health;
    // Image representing the enemy visually
    protected Image image;
    // Width and height of the enemy image
    protected int width, height;

    public Enemy(int x, int y, int speed, int health, String imagePath, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = health;
        this.image = new ImageIcon(imagePath).getImage(); // Load the image from the given path
        this.width = width;
        this.height = height;
    }

    // Method to update the enemy's position
    public void move() {
        y += speed; // Move the enemy down the screen by its speed
    }

    // Method to draw the enemy on the screen
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null); // Draw the enemy image at its current position
    }

    // Method to check if the enemy has moved off the bottom of the screen
    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight; // Returns true if the enemy is below the screen
    }
}



