package com.example.pinguin.Levels;



import com.almasb.fxgl.entity.Entity;
import com.example.pinguin.WallComponent;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Level2 implements Level {
    private Entity ball;
    private Entity ball2;
    private Entity ball11;
    private Entity ball12;
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
        movingWall = spawn("movingwall",300,300);
        ball11 = spawn("ball",895,200);
        ball2 = spawn("ball", 145, 200);
        ball12 = spawn("ball",895,300);
        ballList.add(ball11);
        ballList.add(ball12);
        ballList.add(ball2);

    }
    public void wallSwap() {
        if(lowerWall != null){
            movingWall.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);



        }
    }
}

