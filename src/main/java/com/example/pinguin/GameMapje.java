public class GameMapje {
    package com.example.pinguin;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.animation.*;
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
import java.util.Objects;


import static com.almasb.fxgl.dsl.FXGL.*;


    public class GameMapje extends GameApplication {
        private MediaPlayer mediaPlayer;
        private MediaPlayer secondMediaPlayer;


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
                if(player.getY() <= 50) return;
                else {
                    player.translateY(-5); // move down 5 pixels
                    inc("pixelsMoved", -5);
                }
            });

            onKey(KeyCode.ENTER,  () -> {
                if(ball.getX() <= 300) return;
                else{
                    ball.translateX(-10);
                    inc("pixelsMoved", -10);
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
        private Entity wall;


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
                    .at(900, 100)
                    .viewWithBBox(new Rectangle(25, 25, Color.RED))
                    .with(new CollidableComponent(true))
                    .type(EntityTypes.BALL)
                    .buildAndAttach();

            Rectangle wallView = new Rectangle(50, 150, Color.BROWN);
            wall = entityBuilder()
                    .at(500, 50)
                    .viewWithBBox(wallView)
                    .with(new CollidableComponent(true))
                    .type(EntityTypes.WALL)
                    .buildAndAttach();

            Duration duration = Duration.seconds(3);
            double distance = 200;

            TranslateTransition tt = new TranslateTransition(duration, wallView);
            tt.setToY(wallView.getY() + distance);
            tt.setInterpolator(Interpolator.EASE_BOTH);
            tt.setCycleCount(Animation.INDEFINITE);
            tt.setAutoReverse(true);
            tt.play();


            initPhysics();


            Media intropath = new Media(Paths.get("C:\\Users\\jbuck\\OneDrive - Hogeschool Leiden\\Programeren\\IDK\\idea\\src\\main\\java\\com\\example\\pinguin\\Intro_mp3.mp3").toUri().toString());
            mediaPlayer = new MediaPlayer(intropath);

            Media secondPath = new Media(Paths.get("C:\\Users\\jbuck\\OneDrive - Hogeschool Leiden\\Programeren\\IDK\\idea\\src\\main\\java\\com\\example\\pinguin\\battle_01_mp3.mp3").toUri().toString());
            secondMediaPlayer = new MediaPlayer(secondPath);
        }

        @Override
        protected void initPhysics() {
            FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.BALL, EntityTypes.WALL) {
                @Override
                protected void onCollisionBegin(Entity ball, Entity wall) {
                    if (ball.isColliding(wall)) {
                        ball.translateX(50);
                    }
                }
            });
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
                getGameWorld().addEntities(player, player2, ball, wall);
            });

            // Set the game scene background
            getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/Snow.png?alt=media&token=0213e269-de1b-4212-9ea5-8be1e67b9bc0", 1100, 600, false, true));
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
}
