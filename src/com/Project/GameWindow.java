package com.Project;
import javax.swing.JFrame;

// Defines the main game window by extending JFrame, which provides the basic window features.
public class GameWindow extends JFrame {
    
    // Constructor for GameWindow. Sets up the window with its properties and content.
    public GameWindow() {
        setTitle("Plane Game"); // Sets the title of the window.
        setSize(600, 600); // Sets the size of the window to 600x600 pixels.
        setLocationRelativeTo(null); // Centers the window on the screen.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the default close operation to exit the application.
        add(new GamePanel()); // Adds an instance of GamePanel to the window, which is where the game will be drawn.
    }

    public static void main(String[] args) {
        // Ensures that the window is created and shown in the Event-Dispatching thread for thread safety.
        java.awt.EventQueue.invokeLater(() -> {
            new GameWindow().setVisible(true); // Creates an instance of GameWindow and makes it visible.
        });
    }
}




