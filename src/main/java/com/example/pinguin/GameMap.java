package com.example.pinguin;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameMap extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {

        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("PinguinzWarz");
        settings.setVersion("0.1");

    }

    @Override
    protected void initInput() {

        onKey(KeyCode.W, () -> {

            player.translateY(-5); // move up 5 pixels
            inc("pixelsMoved", -5);
            if(player.getY() <= 50) return;
            else {
                player.translateY(-5); // move down 5 pixels
                inc("pixelsMoved", -5);
            }
        });

        onKey(KeyCode.S, () -> {
            if(player.getY() >= 500) return;
            else {
                player.translateY(5); // move down 5 pixels
                inc("pixelsMoved", +5);
            }
        });

        onKey(KeyCode.UP, () -> {
            if(player2.getY() <= 50) return;
            else {
                player2.translateY(-5); // move down 5 pixels
                inc("pixelsMoved", -5);
            }
        });

        onKey(KeyCode.DOWN, () -> {
            if(player2.getY() >= 500) return;
                else {
                player2.translateY(5); // move down 5 pixels
                inc("pixelsMoved", +5);
            }
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    private Entity player;
    private Entity player2;

    private Entity ball;

    @Override
    protected void initGame() {



        player = entityBuilder()
                .at(100, 100)
                .view(new Rectangle(25, 25, Color.BLUE))
                .buildAndAttach();
        player2 = entityBuilder()
                .at(1000, 100)
                .view(new Rectangle(25, 25, Color.RED))
                .buildAndAttach();
        ball = entityBuilder()
                .at(1000, 100)
                .view(new Rectangle(25, 25, Color.RED))
                .buildAndAttach();
    }

    @Override
    protected void initUI() {

        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100

        textPixels.textProperty().bind(getWorldProperties().intProperty("pixelsMoved").asString());

        getGameScene().addUINode(textPixels); // add to the scene graph
//then you set to your node
        getGameScene().setBackgroundRepeat(new Image("D:\\Informatica\\Challengeweek\\SE Challengeweek\\PinguinzWarz\\src\\main\\resources\\com\\example\\pinguin\\Images\\Snow.png",1100,600,false,true));
    }

    public static void main(String[] args) {
        launch(args);
    }
}