package com.example.pinguin;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BallEntity {
    private Entity entity;

    public BallEntity(double x, double y, Color color) {
        entity = FXGL.entityBuilder()
                .at(x, y)
                .view(new Rectangle(25, 25, color))
                .buildAndAttach();
    }

    public Entity getEntity() {
        return entity;
    }
}
