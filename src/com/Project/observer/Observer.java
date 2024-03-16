package com.Project.observer;
import com.Project.entities.Enemy;

// The Observer interface defines a common protocol for objects that should be notified of changes in a subject.
// It is a crucial part of the Observer design pattern, enabling a one-to-many dependency between objects
// so that when one object changes state, all its dependents are notified and updated automatically.
public interface Observer {
    // The update method is called when the subject (Event in this case) changes state.
    // This method defines how the observer reacts to the state change of the observed object.
    // enemy: The Enemy object that has undergone a change. This parameter allows the observer
    // to access the state of the enemy that triggered the notification.
    void update(Enemy enemy);
}


