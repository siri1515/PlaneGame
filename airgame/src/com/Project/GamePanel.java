package com.Project;
// Color class for defining colors.
import java.awt.Color;
// Graphics class for drawing objects and text in components.
import java.awt.Graphics;
// Classes for handling keyboard events.
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
// List interface for managing collections of objects.
import java.util.List;
// A thread-safe version of ArrayList for managing collections that are expected to be modified by multiple threads.
import java.util.concurrent.CopyOnWriteArrayList;

// Swing component for creating panels within a window.
import javax.swing.JPanel;

import com.Project.entities.Bullet;
import com.Project.entities.Enemy;
import com.Project.entities.PlayerPlane;
import com.Project.factory.EnemyFactory;
import com.Project.observer.BulletObserver;
import com.Project.observer.EnemyObserver;
import com.Project.observer.GameTheme;
import com.Project.strategy.DoubleBulletStrategy;
import com.Project.strategy.ShootingContext;
import com.Project.strategy.SingleBulletStrategy;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    // Game loop control thread
    private Thread gameThread;
    // Player's plane
    private PlayerPlane playerPlane;
    // Thread-safe lists for enemies and bullets
    private List<Enemy> enemies = new CopyOnWriteArrayList<>();
    private List<Bullet> bullets = new CopyOnWriteArrayList<>();
    // Context for shooting strategy
    private ShootingContext shootingContext = new ShootingContext();
    // Flag to track current bullet strategy
    private boolean isUsingSingleBulletStrategy = true;
    // Timing control for shooting
    private long lastShootTime = 0;
    private final long SHOOT_INTERVAL = 1000;

    private int score = 0;

    private GameTheme gameTheme = new GameTheme();



    public GamePanel() 
    {
        setBackground(Color.BLACK); // Sets the background color of the panel.
        playerPlane = new PlayerPlane(300, 500); // Initializes the player's plane with starting position.
        addKeyListener(this); // Adds this panel as a KeyListener.
        setFocusable(true); // Allows the panel to gain focus for key events.
        requestFocusInWindow(); // Requests focus to ensure key inputs are captured.
        startGame(); // Starts the game loop.


    }

    

    // Handles key press events for controlling the game.
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z: // Toggles the bullet shooting strategy.
                if (isUsingSingleBulletStrategy) {
                    shootingContext.setBulletStrategy(new DoubleBulletStrategy());
                    BulletObserver bulletObserver = new BulletObserver(null);
                    gameTheme.addObserver(bulletObserver);
                    isUsingSingleBulletStrategy = false;
                } else {
                    shootingContext.setBulletStrategy(new SingleBulletStrategy());
                    BulletObserver bulletObserver = new BulletObserver(null);
                    gameTheme.addObserver(bulletObserver);
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

	// Adds enemies to the game at set intervals
	// Using EnemyFactory
    private void addEnemies() 
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime % 7000 < 20) { // Every 7 seconds add a Boss enemy
            enemies.add(EnemyFactory.createEnemy("Boss", (int) (Math.random() * getWidth()), -100));
        } else if (currentTime % 1000 < 20) { // Every second add a Normal enemies
            enemies.add(EnemyFactory.createEnemy("Normal", (int) (Math.random() * getWidth()), -50));
        }
        EnemyObserver enemyObserver = new EnemyObserver(null);
        gameTheme.addObserver(enemyObserver);
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

    public static double calculateDistance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x2 - x1, 2)+Math.pow(y2 - y1, 2));
    }

    private void checkBulletPlaneCollisions()
    {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for(Bullet bullet : bullets)
        {
            for(Enemy enemy :enemies)
            {
                
                if(bullet.getX() < enemy.getX() + enemy.getWidth() &&
                bullet.getX() + Bullet.WIDTH > enemy.getX() &&
                bullet.getY() < enemy.getY() + enemy.getHeight() &&
                bullet.getY() + Bullet.HEIGHT > enemy.getY())
                {
                    bulletsToRemove.add(bullet);
                    enemiesToRemove.add(enemy);
                    score += 5;
                }
            }
            
        }

        bullets.removeAll(bulletsToRemove);
        enemies.removeAll(enemiesToRemove);
    }

	@Override
	public void run() {
		// The game loop runs indefinitely to keep the game active.
		while (true) {
			// Calls method to generate new enemies at defined intervals.
			addEnemies();		
			// Updates the position of Enemies, removing any that are off-screen.
			updateEnemies();		

            
			// Capture the current time
			long currentTime = System.currentTimeMillis();
			// Checks if it's time to shoot again based on the shooting interval.
			if (currentTime - lastShootTime > SHOOT_INTERVAL) {
				// Executes the current shooting strategy to shoot bullets, strategy design pattern
				shootingContext.executeStrategy(bullets, playerPlane.getX(), playerPlane.getY());
				// Updates the last shooting time to the current time.
				lastShootTime = currentTime;
			}
			
			// Updates the position of bullets, removing any that are off-screen.
			updateBullets();			

            
			// Updates the player's plane position based on user input.
			playerPlane.updatePosition(); 			
            checkBulletPlaneCollisions();
			// Repaints the game panel to reflect any changes in the game state.
			repaint();
	
			// Pauses the game loop briefly to control the update rate of the game.
			try {
				Thread.sleep(10); // 20 milliseconds pause for a game update rate of 50 FPS approximately.
			} catch (InterruptedException e) {
				e.printStackTrace(); // Prints an error message if the thread's sleep is interrupted.
			}
		}
	}	

	@Override
	protected void paintComponent(Graphics g) {
		// Calls the superclass's paintComponent method to handle standard painting tasks.
		super.paintComponent(g);		
		// Draws the player's plane on the panel.
		playerPlane.draw(g);	
		// Iterates through each bullet in the bullets list to draw them.
		// This loop calls the draw method of each Bullet object,

        displayScore(g);

		for (Bullet bullet : bullets) {
			bullet.draw(g);
		}		
		// Iterates through each enemy in the enemies list to draw them.
		// This loop calls the draw method of each Enemy object,
		// This ensures that all enemies are rendered in their current positions.
		for (Enemy enemy : enemies) {
			enemy.draw(g); // Draw each enemy on the panel
		}
	}

    public void displayScore(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score,10, 10);
    }
}

