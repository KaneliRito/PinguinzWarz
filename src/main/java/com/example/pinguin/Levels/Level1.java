package com.example.pinguin.Levels;

import com.almasb.fxgl.entity.Entity;
import com.example.pinguin.WallComponent;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Level1 implements Level {
    private Entity ball;
    private Entity ball2;
    private Entity ball3;
    private Entity ball4;
    private Entity ball5;
    private Entity ball6;
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
        movingWall2 = spawn("movingwall",500,200);
        movingWall3 = spawn("movingwall",700,400);
        ball = spawn("ball", 155, 100);
        ball2 = spawn("ball", 155, 200);
        ball3 = spawn("ball", 155, 300);
        ball4 = spawn("ball", 895, 100);
        ball5 = spawn("ball", 895, 200);
        ball6 = spawn("ball", 895, 300);
        ballList.add(ball);
        ballList.add(ball2);
        ballList.add(ball3);
        ballList.add(ball4);
        ballList.add(ball5);
        ballList.add(ball6);
    }
    public void wallSwap() {
        if(lowerWall != null){
            movingWall.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);
            movingWall2.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);
            movingWall3.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);}
    }
}
