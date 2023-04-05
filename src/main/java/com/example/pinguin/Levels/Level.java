package com.example.pinguin.Levels;

import com.almasb.fxgl.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public interface Level {
    public List<Entity> ballList= new ArrayList<>();
    public List<Entity> wallList= new ArrayList<>();
    Entity upperWall = null;
    Entity lowerWall = null;

    public Entity getUpperWall();

    public Entity getLowerWall();

    abstract void spawnEntity();
}
