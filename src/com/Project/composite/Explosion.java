package com.Project.composite;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Explosion implements ExplosionComponent {
    private long startTime;
    private int duration;
    private int x, y;
	private Image image;
	private int width, height;

    public Explosion(int x, int y, int duration) {
        this.x = x;
        this.y = y;
		this.width = 100;
        this.height = 100;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
		ImageIcon img = new ImageIcon(getClass().getResource("/images/explosion.png"));
        this.image = img.getImage();
    }

    @Override
    public void draw(Graphics g) {
		if (!isFinished()) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    @Override
    public boolean isFinished() {
        long elapsed = System.currentTimeMillis() - startTime;
        return elapsed > duration;
    }
}
