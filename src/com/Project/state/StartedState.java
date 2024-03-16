package com.Project.state;


import com.Project.GamePanel;

public class StartedState implements GameState {
    private GamePanel panel;

    public StartedState(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void enterState() {
        panel.showStartButton(false);
    }
}