package com.Project.composite;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

// The Explosion class represents a single explosion effect in the game.
// It implements the ExplosionComponent interface, making it a "leaf" in the Composite pattern structure.
public class Explosion implements ExplosionComponent {
    // The time when the explosion effect started. Used to calculate the duration of the effect.
    private long startTime;
    // The duration for which the explosion effect should last, in milliseconds.
    private int duration;
    // The x and y coordinates where the explosion effect should be rendered on the screen.
    private int x, y;
    // The image representing the explosion visual.
	private Image image;
    // The width and height of the explosion image. Determines how large the explosion appears.
	private int width, height;

    // Constructor for the explosion effect.
    // x, y: The initial position of the explosion on the screen.
    // duration: How long (in milliseconds) the explosion effect should last.
    public Explosion(int x, int y, int duration) {
        this.x = x;
        this.y = y;
        this.width = 100; // Default width of the explosion.
        this.height = 100; // Default height of the explosion.
        this.duration = duration; // Duration the explosion effect lasts.
        this.startTime = System.currentTimeMillis(); // Record the start time of the effect.
        // Load the explosion image from resources.
        ImageIcon img = new ImageIcon(getClass().getResource("/images/explosion.png"));
        this.image = img.getImage(); // Retrieve the Image object from the ImageIcon.
    }

    // Render the explosion effect on the screen if it has not finished.
    @Override
    public void draw(Graphics g) {
        if (!isFinished()) {
            g.drawImage(image, x, y, width, height, null); // Draw the explosion at its coordinates.
        }
    }

    // Check if the explosion effect's duration has elapsed, indicating it's finished.
    @Override
    public boolean isFinished() {
        long elapsed = System.currentTimeMillis() - startTime; // Calculate how long the effect has lasted.
        return elapsed > duration; // Return true if the duration has passed, false otherwise.
    }
}

