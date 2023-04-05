package com.example.pinguin;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.example.pinguin.Levels.Level;
import javafx.scene.input.KeyCode;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getInput;

public class InputController {
    private PlayerComponent player;
    private PlayerComponent player2;
    private Level level;

    public void setPlayer(PlayerComponent player) {
        this.player = player;
    }

    public void setPlayer2(PlayerComponent player2) {
        this.player2 = player2;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public InputController(PlayerComponent player, PlayerComponent player2, Level level){
        this.player = player;
        this.player2 = player2;
        this.level = level;
    }

    public void initInput(){
        //levels.get(currentLevel -1)
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                player.up(level.getUpperWall());
            }

            @Override
            protected void onActionEnd() {
                player.stop();
            }
        }, KeyCode.W);
        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.up(level.getLowerWall());
            }

            @Override
            protected void onActionEnd() {
                player.stop();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Shoot") {
            @Override
            protected void onAction() {
                for(int i = 0; i < level.ballList.size();i++ ){
                    player.shootBall(level.ballList.get(i));
                }
            }

            @Override
            protected void onActionEnd() {
                player.stop();;
            }
        }, KeyCode.D);
        getInput().addAction(new UserAction("Up2") {
            @Override
            protected void onAction() {
                player2.up(level.getUpperWall());
            }

            @Override
            protected void onActionEnd() {
                player2.stop();;
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Down2") {
            @Override
            protected void onAction() {
                player2.up(level.getLowerWall());
            }

            @Override
            protected void onActionEnd() {
                player2.stop();
            }
        }, KeyCode.DOWN);
    }

//    public void init() {
//
//
//        onKey(KeyCode.W, () -> movePlayer(player, -5));
//        onKey(KeyCode.S, () -> movePlayer(player, 5));
//        gameMap.onKey(KeyCode.UP, () -> movePlayer(player2, -5));
//        gameMap.onKey(KeyCode.DOWN, () -> movePlayer(player2, 5));
//    }
//
//    private void movePlayer(Entity player, int delta) {
//        if (player.getY() + delta < 50 || player.getY() + delta > 500) {
//            return;
//        }
//        player.translateY(delta);
//        ((GameMap) FXGL.getApp()).inc("pixelsMoved", delta);
//    }
}

