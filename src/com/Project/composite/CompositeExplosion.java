package com.Project.composite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeExplosion implements ExplosionComponent {
    private List<ExplosionComponent> components = new ArrayList<>();
	private List<Long> delays = new ArrayList<>();
	private long startTime;

	public CompositeExplosion() {
        this.startTime = System.currentTimeMillis();
    }

    public void addComponent(ExplosionComponent component, long delay) {
        components.add(component);
        delays.add(delay); 
    }

    @Override
    public void draw(Graphics g) {
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < components.size(); i++) {
            long delay = delays.get(i);
            ExplosionComponent component = components.get(i);
            if (currentTime >= startTime + delay && !component.isFinished()) {
                component.draw(g);
            }
        }
    }

    @Override
    public boolean isFinished() {
        for (ExplosionComponent component : components) {
            if (!component.isFinished()) {
                return false;
            }
        }
        return true;
    }
}

