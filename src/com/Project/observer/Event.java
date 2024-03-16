package com.Project.observer;

// The Event interface is part of the Observer design pattern.
// It defines the contract for objects (events in the game) that can be observed by Observer objects.
public interface Event {
    // registerObserver method allows an Observer to subscribe to this event.
    // When the event occurs, all registered observers will be notified.
    // observer: The Observer instance that wants to start receiving updates from this event.
    public void registerObserver(Observer observer);

    // removeObserver method allows an Observer to unsubscribe from this event.
    // It stops the observer from receiving updates about this event.
    // observer: The Observer instance that no longer wishes to receive updates from this event.
    public void removeObserver(Observer observer);

    // notifyObservers method is called to notify all registered observers when the event occurs.
    // It loops through all the subscribed observers and calls their update method,
    // passing relevant data if necessary.
    public void notifyObservers();
}

