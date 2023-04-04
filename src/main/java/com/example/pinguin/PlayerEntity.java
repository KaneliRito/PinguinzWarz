package com.example.pinguin;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerEntity {
    private com.almasb.fxgl.entity.Entity entity;

    public PlayerEntity(double x, double y, Color color) {
        entity = FXGL.entityBuilder()
                .at(x, y)
                .bbox(new HitBox(BoundingShape.box(52, 62)))
                .with(new AnimationComponent())
                .view(new Rectangle(25, 25, color))
                .buildAndAttach();
    }

    public void moveUp() {
        if (entity.getY() <= 50) return;
        entity.translateY(-5);
        FXGL.<Integer>getWorldProperties().increment("pixelsMoved", -5);
    }

    public void moveDown() {
        if (entity.getY() >= 500) return;
        entity.translateY(5);
        FXGL.<Integer>getWorldProperties().increment("pixelsMoved", 5);
    }

    public com.almasb.fxgl.entity.Entity getEntity() {
        return entity;
    }
}

