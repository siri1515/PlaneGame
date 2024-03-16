package com.Project.state;

import com.Project.GamePanel;

public class NotStartedState implements GameState {
    private GamePanel panel;

    public NotStartedState(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void enterState() {
        panel.showStartButton(true);
    }
}
