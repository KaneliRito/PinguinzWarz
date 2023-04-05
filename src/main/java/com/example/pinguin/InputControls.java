package com.example.pinguin;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;

public class InputControls {
    private Entity player;
    private Entity player2;

    public InputControls(Entity player, Entity player2) {
        this.player = player;
        this.player2 = player2;
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

