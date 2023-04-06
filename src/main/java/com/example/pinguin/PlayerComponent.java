package com.example.pinguin;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {
    private static final double PLAYER_SPEED = 420;

    public PhysicsComponent physics;


    public void up(Entity upperWall) {
        if (entity.getY() >= upperWall.getBottomY() + PLAYER_SPEED / 60)
            physics.setVelocityY(-PLAYER_SPEED);
        else
            stop();
    }

    public void down(Entity bottomWall) {
        //FXGL.getAppHeight()
        if (entity.getBottomY() <= bottomWall.getY()  - bottomWall.getHeight() - (PLAYER_SPEED / 60))
            physics.setVelocityY(PLAYER_SPEED);
        else
            stop();
    }

    public void shootBall(Entity ball){
        if(ball.getY() -30 <= entity.getY() &&entity.getY() <= ball.getY() && entity.getX() <= ball.getX() + 5){
            ball.getComponent(BallComponent.class).setTouched(true);
            System.out.println("Shooting");
        }
    }

    public void stop() {
        physics.setLinearVelocity(0, 0);
    }
}
