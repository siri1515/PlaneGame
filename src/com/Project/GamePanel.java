package com.Project;
import com.Project.entities.PlayerPlane;
import com.Project.entities.ScoreboardUI;
import com.Project.strategy.DoubleBulletStrategy;
import com.Project.strategy.ShootingContext;
import com.Project.strategy.SingleBulletStrategy;
import com.Project.factory.EnemyFactory;
import com.Project.flyweight.EnemyFlyweightFactory;
import com.Project.observer.ScoreManager;
import com.Project.state.GameState;
import com.Project.state.NotStartedState;
import com.Project.state.StartedState;
import com.Project.entities.Bullet;
import com.Project.entities.Enemy;
import com.Project.composite.*;

// Swing component for creating panels within a window.
import javax.swing.JPanel;
// JPanel is a container that can hold a group of components, including other panels or UI elements. In game development, it's commonly used as the drawable surface where game graphics are rendered.

// Graphics class for drawing objects and text in components.
import java.awt.Graphics;
// The Graphics class is crucial for game rendering. It provides drawing methods that allow you to render shapes, text, images, and custom graphics onto the JPanel or any other drawable component.

// Color class for defining colors.
import java.awt.Color;
// Color is used to define colors for drawing and rendering in the game. It can enhance the visual appeal, denote different game states, or distinguish between game entities.

import javax.swing.JButton;
// JButton is a Swing component that creates a clickable button. In games, buttons can be used to start the game, pause, open menus, or trigger any in-game actions.

// Classes for handling keyboard events.
import java.awt.event.*;
// These imports include KeyEvent, KeyListener, and other event classes. They're used to capture and respond to keyboard actions, allowing players to interact with the game through key presses.

// List interface for managing collections of objects.
import java.util.List;
import java.util.ArrayList;
// The List interface and ArrayList class are used to manage collections of objects, such as game entities (enemies, bullets, power-ups). They support dynamic arrays that can grow as needed.

// A thread-safe version of ArrayList for managing collections that are expected to be modified by multiple threads.
import java.util.concurrent.CopyOnWriteArrayList;
// CopyOnWriteArrayList is a thread-safe variant of ArrayList. It's particularly useful in game development for collections that are accessed and modified by multiple threads, such as the game loop thread and UI event-handling thread, ensuring consistency and avoiding concurrency issues.

// Extends JPanel to create a custom panel for the game, implementing Runnable for the game loop and KeyListener for keyboard input.
public class GamePanel extends JPanel implements Runnable, KeyListener {
    // Variable for controlling the game's main loop.
    private Thread gameThread;
    // Represents the player's plane in the game.
    private PlayerPlane playerPlane;
    // Factory for creating enemy objects.
	private EnemyFactory enemyFactory;
    // Lists to manage enemies and bullets in the game, ensuring thread safety.
    private List<Enemy> enemies = new CopyOnWriteArrayList<>();
    private List<Bullet> bullets = new CopyOnWriteArrayList<>();
    // Manages the shooting strategy (single or double bullets).
    private ShootingContext shootingContext = new ShootingContext();
    // Flag to switch between single and double bullet strategies.
    private boolean isUsingSingleBulletStrategy = true;
    // Controls the timing for shooting actions.
    private long lastShootTime = 0;
    private final long SHOOT_INTERVAL = 1000; // Interval between shots.
    // Manages the game's scoring system.
	private ScoreManager scoreManager = new ScoreManager();
    // UI component to display the score.
	private ScoreboardUI scoreboardUI = new ScoreboardUI(scoreManager);
    // Manages explosion effects.
	private List<ExplosionComponent> explosions = new CopyOnWriteArrayList<>();
    // Current state of the game (e.g., not started, started).
	private GameState currentState;
    // Button to start the game.
    private JButton startButton;

    // Constructor: sets up the game panel and initializes game components.
    public GamePanel() {
        setBackground(Color.BLACK); // Set the background color of the panel.
        initializeStartButton(); // Initialize the start button.
        playerPlane = new PlayerPlane(300, 500); // Create the player's plane.
        EnemyFlyweightFactory flyweightFactory = new EnemyFlyweightFactory(); // For optimizing memory usage with enemies.
        enemyFactory = new EnemyFactory(flyweightFactory); // Factory for creating enemies.
        setState(new NotStartedState(this)); // Set initial game state.
        addKeyListener(this); // Listen for keyboard events.
        setFocusable(true); // Enable the panel to receive focus.
        requestFocusInWindow(); // Request focus to capture keyboard events.
        startGame(); // Start the game loop.
    }

    // Handles key press events for controlling the game.
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z: // Toggles the bullet shooting strategy.
                if (isUsingSingleBulletStrategy) {
                    shootingContext.setBulletStrategy(new DoubleBulletStrategy());
                    isUsingSingleBulletStrategy = false;
                } else {
                    shootingContext.setBulletStrategy(new SingleBulletStrategy());
                    isUsingSingleBulletStrategy = true;
                }
                break;
            case KeyEvent.VK_A: // Moves the player's plane left.
                playerPlane.setMovingLeft(true);
                break;
            case KeyEvent.VK_D: // Moves the player's plane right.
                playerPlane.setMovingRight(true);
                break;
        }
    }

    // Handles key release events
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: // Stops moving the player's plane left.
                playerPlane.setMovingLeft(false);
                break;
            case KeyEvent.VK_D: // Stops moving the player's plane right.
                playerPlane.setMovingRight(false);
                break;
        }
    }

    // Must required for KeyListener interface but not used.
    @Override
    public void keyTyped(KeyEvent e) {
    }

	// Updates the position of bullets and removes those off the screen.
	private void updateBullets() {
        bullets.forEach(Bullet::move);
		for (Bullet bullet : bullets) {
            bullet.move();
        }
        bullets.removeIf(bullet -> bullet.isOffScreen(getHeight()));
    }

	public void createEnemy() {
		// Capture the current system time in milliseconds to determine when to spawn enemies.
		long currentTime = System.currentTimeMillis();
	
		// Every 7 seconds, spawn a Boss enemy. This is achieved by checking if the current time modulo 7000ms falls within a 20ms window.
		if (currentTime % 7000 < 20) {
			// Utilizes the EnemyFactory to create a "Boss" enemy. The factory decides the specific enemy type based on the "Boss" string,
			// and positions the enemy at a random horizontal location within the game panel and just above the visible area (-100 pixels in the Y direction).
			// This approach demonstrates the use of the Factory Pattern for object creation, and the Flyweight Pattern for optimizing memory usage by reusing enemy state.
			Enemy enemy = enemyFactory.createEnemy("Boss", (int) (Math.random() * getWidth()), -100);
			// Add the newly created boss enemy to the thread-safe list of enemies managed by the game.
			enemies.add(enemy); 
		} 
		// Similarly, every second, spawn a Normal enemy. This uses the same time-check strategy as above but with a 1000ms (1 second) interval.
		else if (currentTime % 1000 < 20) {
			// Creates a "Normal" enemy using the same factory and pattern applications as the "Boss" enemy,
			// but places these enemies slightly closer to the top of the screen (-50 pixels).
			Enemy enemy = enemyFactory.createEnemy("Normal", (int) (Math.random() * getWidth()), -50);
			// Adds the normal enemy to the game's enemy list, ensuring it will be updated and rendered in the game loop.
			enemies.add(enemy); 
		}
	}
	

	// Updates the position of enemy and removes those off the screen.
    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
		enemies.removeIf(enemy -> enemy.isOffScreen(getHeight()));
    }

    // Initializes and starts the game loop thread.
    private void startGame() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

	private void checkCollisions() {
		// Lists to track bullets and enemies that need to be removed from the game.
		List<Bullet> bulletsToRemove = new ArrayList<>();
		List<Enemy> enemiesToRemove = new ArrayList<>();
	
		// Iterate through each bullet currently in the game.
		for (Bullet bullet : bullets) {
			// Check each bullet against every enemy to see if a collision occurred.
			for (Enemy enemy : enemies) {
				// If an enemy is hit by a bullet, mark the bullet for removal and reduce the enemy's health.
				if (enemy.isHit(bullet)) {
					bulletsToRemove.add(bullet); // Mark the bullet for removal from the game.
					enemy.reduceHealth(bullet.getDamage()); // Reduce the enemy's health based on the bullet's damage.
					// If the enemy's health drops to zero or below, it's defeated.
					if (enemy.getHealth() <= 0) {
						scoreManager.update(enemy); // Update the score to reflect the defeated enemy.
						enemiesToRemove.add(enemy); // Mark the enemy for removal from the game.
	
						// Create a composite explosion effect at the enemy's location to visually represent the defeat.
						CompositeExplosion explosion = new CompositeExplosion();
						explosion.addComponent(new Explosion(enemy.getX(), enemy.getY(), 2000), 0);
						explosion.addComponent(new Smoke(enemy.getX(), enemy.getY(), 2000), 1000);
						explosions.add(explosion); // Add the explosion effect to the list to be rendered.
					}
					break; // Exit the loop early since a bullet can only hit one enemy.
				}
			}
		}
	
		// Remove all marked bullets and enemies from their respective lists.
		bullets.removeAll(bulletsToRemove);
		enemies.removeAll(enemiesToRemove);
	}
	

// Initializes the start button with its properties and action listener.
private void initializeStartButton() {
    startButton = new JButton("Start"); // Create a new button with the label "Start".
    // Add an action listener to the button. When clicked, it changes the game's state to Started.
    startButton.addActionListener(e -> setState(new StartedState(this)));
    add(startButton); // Add the button to the JPanel (the game panel).
    startButton.setVisible(false); // Initially, the start button is not visible.
}

// Controls the visibility of the start button within the game panel.
public void showStartButton(boolean show) {
    startButton.setVisible(show); // Set the visibility of the start button based on the 'show' parameter.
}

// Sets the game's current state to a new state and initiates the state's behavior.
public void setState(GameState newState) {
    this.currentState = newState; // Update the current state to the new state.
    newState.enterState(); // Call the enterState method of the new state to perform any initial actions required by this state.
}


// Method to render game content like the player's plane, bullets, enemies, and explosions.
private void renderGameContent(Graphics g) {
    playerPlane.draw(g); // Draw the player's plane on the game panel.
    
    // Loop through and draw each bullet in the game.
    for (Bullet bullet : bullets) {
        bullet.draw(g);
    }
    
    // Loop through and draw each enemy in the game.
    for (Enemy enemy : enemies) {
        enemy.draw(g);
    }

    // Loop through and draw each explosion effect.
    for (ExplosionComponent explosion : explosions) {
        explosion.draw(g);
    }
    
    // Draw the game's scoreboard UI.
    scoreboardUI.draw(g);
}

// Main game loop that runs continuously.
@Override
public void run() {
    while (true) {
        createEnemy(); // Spawn new enemies at specified intervals.
        updateEnemies(); // Update enemy positions and check for off-screen removal.
        
        long currentTime = System.currentTimeMillis();
        // Check if enough time has passed to shoot again.
        if (currentTime - lastShootTime > SHOOT_INTERVAL) {
            // Execute the current shooting strategy (single or double bullets).
            shootingContext.executeStrategy(bullets, playerPlane.getX(), playerPlane.getY());
            lastShootTime = currentTime; // Update the time of the last shot.
        }
        
        updateBullets(); // Update bullet positions and remove off-screen bullets.
        playerPlane.updatePosition(); // Update the player's plane position based on input.
        checkCollisions(); // Check for collisions between bullets and enemies.

        repaint(); // Repaint the panel to reflect updated game state.
        
        try {
            Thread.sleep(20); // Pause the loop briefly to achieve a frame rate of ~50 FPS.
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle any interrupt exceptions during sleep.
        }
    }
}

// Custom paint component method to handle game rendering.
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g); // Perform standard painting tasks.
    // Render game content only if the game has started.
    if (currentState instanceof StartedState) {
        renderGameContent(g);
    }
}

	
}


