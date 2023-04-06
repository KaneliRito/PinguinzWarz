package com.example.pinguin.Levels;

import com.almasb.fxgl.entity.Entity;
import com.example.pinguin.BallComponent;
import com.example.pinguin.WallComponent;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Level1 implements Level {
    private Entity ball;
    private Entity ball10;
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
        lowerWall = spawn("sidewall",0,570);
        upperWall = spawn("sidewall",0,0);
        movingWall = spawn("movingwall",300,250);
        ball = spawn("ball", 145, 200);
        ball.getComponent(BallComponent.class).setStartPos(145,200);
        ball10 = spawn("ball",895,200);
        ball10.getComponent(BallComponent.class).setStartPos(895,200);
        ballList.add(ball);
        ballList.add(ball10);

    }
    public void wallSwap() {
        if(lowerWall != null){
            movingWall.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);


        }

}}
