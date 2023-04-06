package com.example.pinguin.Levels;



import com.almasb.fxgl.entity.Entity;
import com.example.pinguin.WallComponent;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Level2 implements Level {
    public List<Entity> getBallList() {
        return ballList;
    }

    private List<Entity> ballList = new ArrayList<>();
    private Entity ball;
    private Entity ball2;
    private Entity ball3;
    private Entity movingWall;
    private Entity movingWall2;
    private Entity movingWall3;


    public void spawnEntity(){
        movingWall = spawn("movingwall",500,250);


        ball = spawn("ball", 155, 100);
        ball2 = spawn("ball", 155, 200);

        ballList.add(ball);
        ballList.add(ball2);

    }
    public void wallSwap(Entity lowerWall, Entity upperWall) {
        if(lowerWall != null){
            movingWall.getComponent(WallComponent.class).swapMovement(lowerWall, upperWall);

        }
    }
}

