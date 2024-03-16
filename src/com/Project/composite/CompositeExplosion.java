package com.Project.composite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

// CompositeExplosion acts as a composite in the Composite design pattern,
// allowing it to contain and manage multiple ExplosionComponent objects, including other composites or leaf objects.
public class CompositeExplosion implements ExplosionComponent {
    // A list to hold child components of this composite.
    private List<ExplosionComponent> components = new ArrayList<>();
    // A corresponding list of delays for when each component should start relative to the composite's creation.
	private List<Long> delays = new ArrayList<>();
	// The start time of the composite, used to calculate when to initiate its components based on their delays.
	private long startTime;

	// Constructor initializing the start time of the composite explosion.
	public CompositeExplosion() {
        this.startTime = System.currentTimeMillis();
    }

    // Adds a new explosion component to the composite with a specified delay before it starts.
    public void addComponent(ExplosionComponent component, long delay) {
        components.add(component); // Add the component to the list.
        delays.add(delay); // Store the delay associated with this component.
    }

    // Draws all child components based on their delays and whether they are finished.
    @Override
    public void draw(Graphics g) {
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < components.size(); i++) {
            long delay = delays.get(i); // Retrieve the delay for the current component.
            ExplosionComponent component = components.get(i); // Retrieve the component.
            // Draw the component if its delay has elapsed and it's not finished.
            if (currentTime >= startTime + delay && !component.isFinished()) {
                component.draw(g);
            }
        }
    }

    // Determines if all child components are finished, indicating the composite is finished.
    @Override
    public boolean isFinished() {
        // Iterate over all components to check if any are still active.
        for (ExplosionComponent component : components) {
            if (!component.isFinished()) {
                return false; // Return false if any component is not finished.
            }
        }
        return true; // Return true if all components are finished.
    }
}
