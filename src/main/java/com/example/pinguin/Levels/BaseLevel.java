package com.example.pinguin.Levels;



import com.almasb.fxgl.entity.Entity;
import com.example.pinguin.WallComponent;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BaseLevel implements Level {
    private Entity ball;
    private Entity ball2;
    private Entity ball3;
    private Entity upperWall;
    private Entity lowerWall;
    private Entity movingWall;
    private Entity movingWall2;
    private Entity movingWall3;

    public Entity getUpperWall() {
        return upperWall;
    }

    public Entity getLowerWall() {
        return lowerWall;
    }

    public void spawnEntity(){
        lowerWall = spawn("sidewall",0,550);
        upperWall = spawn("sidewall",0,50);
        movingWall = spawn("movingwall",300,300);
        ball = spawn("ball", 145, 100);

    }
    public void wallSwap() {
        if(lowerWall != null){
            movingWall.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);


        }

    }}

