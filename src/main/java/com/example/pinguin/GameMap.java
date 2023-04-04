package com.example.pinguin;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;





import static com.almasb.fxgl.dsl.FXGL.*;


public class GameMap extends GameApplication {
    private MediaPlayer mediaPlayer;
    private MediaPlayer secondMediaPlayer;

    private PlayerComponent player;
    private PlayerComponent player2;

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("PinguinzWarz");
        settings.setVersion("0.1");

    }

    @Override
    protected void initInput() {

       /* onKey(KeyCode.W, () -> {
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
        });*/
        onKey(KeyCode.T, () -> {
            ball.getComponent(BallComponent.class).setTouched(true);
            ball2.getComponent(BallComponent.class).setTouched(true);
            ball3.getComponent(BallComponent.class).setTouched(true);
            ball4.getComponent(BallComponent.class).setTouched(true);
            ball5.getComponent(BallComponent.class).setTouched(true);
            ball6.getComponent(BallComponent.class).setTouched(true);
            ball7.getComponent(BallComponent.class).setTouched(true);
            ball8.getComponent(BallComponent.class).setTouched(true);
            ball9.getComponent(BallComponent.class).setTouched(true);

        });
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                player.up();
            }

            @Override
            protected void onActionEnd() {
                player.stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.down();
            }

            @Override
            protected void onActionEnd() {
                player.stop();
            }
        }, KeyCode.S);
        getInput().addAction(new UserAction("Up2") {
            @Override
            protected void onAction() {
                player2.up();
            }

            @Override
            protected void onActionEnd() {
                player2.stop();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Down2") {
            @Override
            protected void onAction() {
                player2.down();
            }

            @Override
            protected void onActionEnd() {
                player2.stop();
            }
        }, KeyCode.DOWN);

        /*onKey(KeyCode.UP, () -> {
            if(player2.getY() <= 50) return;
            else {
                //player2.translateY(-5); // move down 5 pixels
               // inc("pixelsMoved", -5);
            }
        });
        onKey(KeyCode.LEFT, () -> {
            if(player2.getX() <= 50) return;
            else {
                player2.translateX(-5); // move down 5 pixels
                inc("pixelsMoved", -5);
            }
        });
        onKey(KeyCode.RIGHT, () -> {
            if(player2.getX() >= 1050) return;
            else {
                player2.translateX(5); // move down 5 pixels
                inc("pixelsMoved", 5);
            }
        });

        onKey(KeyCode.DOWN, () -> {
            if(player2.getY() >= 500) return;
                else {
                player2.translateY(5); // move down 5 pixels
                inc("pixelsMoved", +5);
            }
        });*/
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    private Entity playerentity;
    private Entity player2entity;

    private Entity ball;
    private Entity ball2;
    private Entity ball3;
    private Entity ball4;
    private Entity ball5;
    private Entity ball6;
    private Entity ball7;
    private Entity ball8;
    private Entity ball9;
    private Entity upperWall;
    private Entity lowerWall;
    private Entity movingWall;

    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new GameEntityFactory());
        initScreenBounds();

        playerentity = spawn("player", 100, 100);
        player2entity = spawn("player2", 1000, 100);
        ball = spawn("ball", 300, 100);;

        player = playerentity.getComponent(PlayerComponent.class);
        player2 = player2entity.getComponent(PlayerComponent.class);
        ball2 = spawn("ball", 400, 100);
        ball3 = spawn("ball", 500, 100);
        ball4 = spawn("ball", 300, 200);
        ball5 = spawn("ball", 400, 200);
        ball6 = spawn("ball", 500, 200);
        ball7 = spawn("ball", 600, 200);
        ball8 = spawn("ball", 500, 100);
        ball9 = spawn("ball", 600, 100);
        lowerWall = spawn("sidewall",0,550);
        upperWall = spawn("sidewall",0,50);
        movingWall = spawn("movingwall",300,300);

        if(movingWall.getY() >=500) movingWall.getComponent(WallComponent.class).setChanged(true);
        if(movingWall.getY() <=400) movingWall.getComponent(WallComponent.class).setChanged(false);

        Media intropath = new Media(Paths.get("D:\\Informatica\\Challengeweek\\SE Challengeweek\\PinguinzWarz\\src\\main\\resources\\assets\\sounds\\Intro_mp3.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(intropath);

        Media secondPath = new Media(Paths.get("D:\\Informatica\\Challengeweek\\SE Challengeweek\\PinguinzWarz\\src\\main\\resources\\assets\\sounds\\battle_01_mp3.mp3").toUri().toString());
        secondMediaPlayer = new MediaPlayer(secondPath);






    }
    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);

        //CollisionHandler ballCharHandler = new CollisionHandler(EntityType.BALL, EntityType.PLAYER) {
           // @Override
           // protected void onCollisionBegin(Entity a, Entity b) {
           //     play("hit_bat.wav");
           // }
       // };

        //getPhysicsWorld().addCollisionHandler(ballCharHandler);
        /*onCollisionBegin(EntityType.PLAYER, EntityType.BALL, (player2, ball) -> {
            ball.getComponent(BallComponent.class).bounce();

        });*/
    }

    @Override
    protected void initUI() {


        mediaPlayer.setAutoPlay(true);
        // Create a rectangle for the homescreen background
        Rectangle homeScreen = new Rectangle(0, 0, getAppWidth(), getAppHeight());
        homeScreen.setFill(Color.BLACK);

        // Create a text for the "Start" button
        Text startText = new Text("Start");
        startText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        startText.setFill(Color.WHITE);

        // Center the "Start" button on the homescreen
        double centerX = getAppWidth() / 2 - startText.getLayoutBounds().getWidth() / 2;
        double centerY = getAppHeight() / 2 - startText.getLayoutBounds().getHeight() / 2;
        startText.setTranslateX(centerX);
        startText.setTranslateY(centerY);

        // Create a group to hold the homescreen elements
        Group homeScreenGroup = new Group(homeScreen, startText);

        // Add the homescreen group to the game scene
        getGameScene().addUINode(homeScreenGroup);

        // Transition to the current screen upon pressing the "Start" button
        startText.setOnMouseClicked(event -> {
            mediaPlayer.stop();
            getGameScene().removeUINode(homeScreenGroup);
            secondMediaPlayer.setAutoPlay(true);
            getGameWorld().addEntities(playerentity, player2entity, ball);
        });

        // Set the game scene background
        getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/Snow.png?alt=media&token=0213e269-de1b-4212-9ea5-8be1e67b9bc0", 1100, 600, false, true));
    }

    private void initScreenBounds() {
        Entity walls = entityBuilder()
                .type(EntityType.WALL)
                .collidable()
                .buildScreenBounds(150);

        getGameWorld().addEntity(walls);
    }

    public static void main(String[] args) {
        launch(args);
    }
}