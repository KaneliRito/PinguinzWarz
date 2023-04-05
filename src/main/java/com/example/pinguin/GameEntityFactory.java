package com.example.pinguin;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
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
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return entityBuilder()
                .from(data)
                .type(EntityType.PLAYER)
                //.viewWithBBox("player.png")
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PlayerComponent())
                .with(new AnimationComponent())
                //.at(1000, 100)
//                .viewWithBBox(new Rectangle(25, 25, Color.BLUE)
                .build();
                //.build();
    }
    @Spawns("player2")
    public Entity newPlayer2(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        AnimationComponent animationComponent = new AnimationComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return entityBuilder()
                .from(data)
                .type(EntityType.PLAYER2)
                //.viewWithBBox("player.png")
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PlayerComponent())
                //.at(1000, 100)
                .with(animationComponent)
//                .viewWithBBox(new Rectangle(25, 25, Color.RED))
                .build();
        //.build();
    }

    @Spawns("ball")
    public Entity newBall(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(1.0f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(5 * 60, -5 * 60));

        return entityBuilder(data)
                .type(EntityType.BALL)
                .view("Ball.png")
                .bbox(new HitBox(BoundingShape.circle(10)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new BallComponent())
                .build();
    }

    @Spawns("sidewall")
    public Entity newSideWall(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return entityBuilder(data)
                .type(EntityType.WALL)
                .viewWithBBox(new Rectangle(1100, 30, Color.BROWN))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();

    }
    @Spawns("movingwall")
    public Entity newMovingWall(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return entityBuilder(data)
                .type(EntityType.WALL)
                .viewWithBBox(new Rectangle(20, 60, Color.BROWN))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new WallComponent())
                .build();

    }
}
