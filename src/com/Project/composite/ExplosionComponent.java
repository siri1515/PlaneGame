package com.Project.composite;
import java.awt.Graphics;

// The ExplosionComponent interface is part of the Composite design pattern. It defines the common operations
// for both composite and leaf nodes in the composition. In the context of your game, it represents components
// of an explosion effect, which can be either simple (leaf) or a combination (composite) of multiple effects.
public interface ExplosionComponent {
    // draw method: Responsible for rendering the explosion component on the screen.
    // This method is called by the game rendering system to display the explosion effect.
    // g: The Graphics object used for drawing operations in the Java AWT/Swing framework.
    public void draw(Graphics g);

    // isFinished method: Determines whether the explosion effect has completed.
    // This could involve checking if an animation has run its course or if a timer has elapsed.
    // Returns true if the component has finished its effect and can be removed from the game screen,
    // false otherwise.
    public boolean isFinished();
}
