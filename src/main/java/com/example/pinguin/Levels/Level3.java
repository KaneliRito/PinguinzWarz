package com.example.pinguin.Levels;

import com.almasb.fxgl.entity.Entity;
import com.example.pinguin.WallComponent;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Level3 implements Level {
    private Entity ball;
    private Entity ball2;
    private Entity ball3;
    private Entity ball11;
    private Entity ball12;
    private Entity ball13;

    private Entity upperWall;
    private Entity lowerWall;
    private Entity movingWall;
    private Entity movingWall2;


    public Entity getUpperWall() {
        return upperWall;
    }

    public Entity getLowerWall() {
        return lowerWall;
    }

    public void spawnEntity(){
        lowerWall = spawn("sidewall",0,570);
        upperWall = spawn("sidewall",0,0);
        movingWall = spawn("movingwall",300,300);
        movingWall2 = spawn("movingwall",500,200);

        ball11 = spawn("ball",895,200);
        ball12 = spawn("ball",895,300);


        ballList.add(ball11);
        ballList.add(ball12);

        ball2 = spawn("ball", 145, 200);
        ball3 = spawn("ball", 145, 300);
        ballList.add(ball13);



    }
    public void wallSwap() {
        if(lowerWall != null){
            movingWall.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);
            movingWall2.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);

        }
    }
}

