package com.Project.entities;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class PlayerPlane extends JPanel {
    // Position of the plane on the panel.
    private int x, y;
    // Flags to track the movement direction.
    private boolean movingLeft = false;
    private boolean movingRight = false;
    // Constants for the speed, width, and height of the plane.
    public static final int SPEED = 2;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    // The image used to represent the plane.
    private Image planeImage;

    // Constructor initializes the plane with a position and loads its image.
    public PlayerPlane(int x, int y) {
        this.x = x;
        this.y = y;
        this.planeImage = new ImageIcon(getClass().getResource("/images/plane2.png")).getImage();
    }

    // Method to draw the plane on the panel.
    public void draw(Graphics g) {
        g.drawImage(planeImage, x, y, WIDTH, HEIGHT, null);
    }

    // Getter for the x-coordinate of the plane.
    public int getX() {
        return x;
    }

    // Getter for the y-coordinate of the plane.
    public int getY() {
        return y;
    }

    // Sets the movingLeft flag, indicating whether the plane should move left.
    public void setMovingLeft(boolean moving) {
        this.movingLeft = moving;
    }
    
    // Sets the movingRight flag, indicating whether the plane should move right.
    public void setMovingRight(boolean moving) {
        this.movingRight = moving;
    }
    
    // Updates the position of the plane based on the movingLeft and movingRight flags.
    public void updatePosition() {
        // Moves the plane left if movingLeft is true and the plane is not at the left edge.
        if (movingLeft && x > 0) {
            x -= SPEED;
        }
        // Moves the plane right if movingRight is true and the plane is not at the right edge.
        // Note: The right edge check uses a hard-coded value (600) which should ideally be the panel width.
        if (movingRight && x < (600-WIDTH)) {
            x += SPEED;
        }
        // Requests a repaint of the component to update the visual representation.
        repaint();
    }
    
    // Overrides the paintComponent method to draw the plane image at its current position.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(planeImage, x, y, WIDTH, HEIGHT, this);
    }

}


