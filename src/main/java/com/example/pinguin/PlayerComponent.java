package com.example.pinguin;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {
    private static final double PLAYER_SPEED = 420;

    public PhysicsComponent physics;

    public void up() {
        if (entity.getY() >= PLAYER_SPEED / 60)
            physics.setVelocityY(-PLAYER_SPEED);
        else
            stop();
    }

    public void down() {
        if (entity.getBottomY() <= FXGL.getAppHeight() - (PLAYER_SPEED / 60))
            physics.setVelocityY(PLAYER_SPEED);
        else
            stop();
    }

    public void stop() {
        physics.setLinearVelocity(0, 0);
    }
}
