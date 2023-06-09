package com.example.pinguin.Levels;



import com.almasb.fxgl.entity.Entity;
import com.example.pinguin.BallComponent;
import com.example.pinguin.WallComponent;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Level4 implements Level {
    private Entity ball2;
    private Entity ball3;
    private Entity ball4;
    private Entity ball5;
    private Entity ball6;
    private Entity ball7;
    private Entity ball11;
    private Entity ball12;
    private Entity ball13;
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
        lowerWall = spawn("sidewall",0,570);
        upperWall = spawn("sidewall",0,0);
        movingWall2 = spawn("movingwall",500,150);
        movingWall3 = spawn("movingwall",700,450);
        ball2 = spawn("ball", 145, 100);
        ball2.getComponent(BallComponent.class).setStartPos(145,100);
        ball3 = spawn("ball", 145, 300);
        ball3.getComponent(BallComponent.class).setStartPos(145,300);
        ball4 = spawn("ball", 145, 400);
        ball4.getComponent(BallComponent.class).setStartPos(145,400);
        ball11 = spawn("ball", 895, 100);
        ball11.getComponent(BallComponent.class).setStartPos(895,100);
        ball12 = spawn("ball", 895, 300);
        ball12.getComponent(BallComponent.class).setStartPos(895,300);
        ball13 = spawn("ball", 895, 400);
        ball13.getComponent(BallComponent.class).setStartPos(895,400);
        ballList.add(ball11);
        ballList.add(ball12);
        ballList.add(ball13);
        ballList.add(ball2);
        ballList.add(ball3);
        ballList.add(ball4);
    }


    public void wallSwap() {
        if(lowerWall != null){
            movingWall2.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);
            movingWall3.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);

        }
    }
}

