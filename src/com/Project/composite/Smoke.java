package com.Project.composite;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

// The Smoke class represents a smoke effect that can be used in the game's explosion animations.
// As an implementation of the ExplosionComponent, it functions as a "leaf" in the composite structure,
// meaning it's one of the basic building blocks that can be combined into larger, composite explosion effects.
public class Smoke implements ExplosionComponent {
    // The time at which the smoke effect was initiated. Used to track the effect's duration.
    private long startTime;
    // The total duration the smoke effect should be visible on the screen, in milliseconds.
    private int duration;
    // The position (x, y coordinates) where the smoke effect should be drawn.
    private int x, y;
    // The image to be used for the smoke effect.
	private Image image;
    // The dimensions (width and height) of the smoke effect, affecting how it's scaled when drawn.
	private int width, height;

    // Constructor initializing the smoke effect with a position, duration, and loading its visual representation.
	public Smoke(int x, int y, int duration) {
        this.x = x;
        this.y = y;
		this.width = 100; // Default width of the smoke effect.
        this.height = 100; // Default height of the smoke effect.
        this.duration = duration; // How long the smoke effect lasts.
        this.startTime = System.currentTimeMillis(); // Marking the start time of the effect.
        // Load the smoke image from the specified path in the resources directory.
		ImageIcon img = new ImageIcon(getClass().getResource("/images/smoke1.png"));
		this.image = img.getImage(); // Extracting the actual Image object to be used in drawing.
    }

    // Draw the smoke effect on the game's canvas if it hasn't finished yet.
    @Override
    public void draw(Graphics g) {
		if (!isFinished()) {
            // Render the smoke image at its position with its specified dimensions.
            g.drawImage(image, x, y, width, height, null);
        }
    }

    // Determine whether the smoke effect's duration has elapsed, indicating it's finished.
    @Override
    public boolean isFinished() {
        // Calculate how much time has passed since the effect started.
        long elapsed = System.currentTimeMillis() - startTime;
        // Check if this elapsed time exceeds the effect's intended duration.
        return elapsed > duration;
    }
}
