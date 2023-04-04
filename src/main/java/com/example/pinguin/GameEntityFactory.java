package com.example.pinguin;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameEntityFactory implements EntityFactory{
    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                //.viewWithBBox("player.png")
                .collidable()
                //.at(1000, 100)
                .view(new Rectangle(25, 25, Color.BLUE))
                .buildAndAttach();
                //.build();
    }
    @Spawns("player2")
    public Entity newPlayer2(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                //.viewWithBBox("player.png")
                .collidable()
                //.at(1000, 100)
                .view(new Rectangle(25, 25, Color.RED))
                .buildAndAttach();
        //.build();
    }

    @Spawns("ball")
    public Entity newBall(SpawnData data) {

        return entityBuilder(data)
                .type(EntityType.BALL)
                .viewWithBBox("Ball.png")
                //.with(new RandomMoveComponent(new Rectangle2D(200, 50, 700, 500), 100))
                .with(new BallComponent())
                .collidable()
                .build();
    }
}
