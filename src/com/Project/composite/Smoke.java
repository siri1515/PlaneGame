package com.Project.composite;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Smoke implements ExplosionComponent {
    private long startTime;
    private int duration;
    private int x, y;
	private Image image;
	private int width, height;

	public Smoke(int x, int y, int duration) {
        this.x = x;
        this.y = y;
		this.width = 100;
        this.height = 100;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
		//ImageIcon img = new ImageIcon(getClass().getResource("/images/smoke1.png"));
		//this.image = new ImageIcon("resources/images/smoke1.png").getImage();
        //this.image = img.getImage(/images/smoke1.png);
		ImageIcon img = new ImageIcon(getClass().getResource("/images/smoke1.png"));
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

