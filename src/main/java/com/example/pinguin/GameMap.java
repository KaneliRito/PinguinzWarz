package com.example.pinguin;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.example.pinguin.Levels.Level1;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.Group;
import com.almasb.fxgl.audio.Music;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;


import static com.almasb.fxgl.dsl.FXGL.*;


public class GameMap extends GameApplication {
    private MediaPlayer mediaPlayer;
    private MediaPlayer secondMediaPlayer;

    private Entity playerentity;
    private Entity player2entity;
    private PlayerComponent player;
    private PlayerComponent player2;
    private Level1 level1;
    //private InputController inputController;

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("PinguinzWarz");
        settings.setVersion("0.1");
        level1 = new Level1();

    }

    @Override
    protected void initInput() {

        onKey(KeyCode.T, () -> {
            System.out.println("x: " + playerentity.getX());
            System.out.println("y: " + playerentity.getY());
        });
        /*if(level1 != null){
            inputController = new InputController(player,player2,level1);
            inputController.initInput();
        }*/
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() { player.up(level1.getUpperWall()); }

            @Override
            protected void onActionEnd() {
                player.stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.down(level1.getLowerWall());
            }

            @Override
            protected void onActionEnd() {
                player.stop();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Shoot") {
            @Override
            protected void onAction() {
                for(int i = 0; i < level1.ballList.size();i++ ){
                    player.shootBall(level1.ballList.get(i));
                }}
            @Override
            protected void onActionEnd() { player.stop();}
        }, KeyCode.D);
        getInput().addAction(new UserAction("Up2") {
            @Override
            protected void onAction() { player2.up(level1.getUpperWall());}
            @Override
            protected void onActionEnd() { player2.stop();}
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Down2") {
            @Override
            protected void onAction() {player2.down(level1.getLowerWall());}

            @Override
            protected void onActionEnd() {
                player2.stop();
            }
        }, KeyCode.DOWN);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
        vars.put("player1score", 0);
        vars.put("player2score", 0);
    }

    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new GameEntityFactory());

        playerentity = spawn("player", 100, 100);
        player2entity = spawn("player2", 1000, 100);
        player = playerentity.getComponent(PlayerComponent.class);
        player2 = player2entity.getComponent(PlayerComponent.class);
        level1.spawnEntity();

        initScreenBounds();

        Media intropath = new Media(Paths.get("D:\\Informatica\\Challengeweek\\SE Challengeweek\\PinguinzWarz\\src\\main\\resources\\assets\\sounds\\Intro_mp3.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(intropath);

        Media secondPath = new Media(Paths.get("D:\\Informatica\\Challengeweek\\SE Challengeweek\\PinguinzWarz\\src\\main\\resources\\assets\\sounds\\battle_01_mp3.mp3").toUri().toString());
        secondMediaPlayer = new MediaPlayer(secondPath);
    }
    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
    }
    private void createButtons(String[] buttonLabels, Group containerGroup) {
        List<Text> buttonList = new ArrayList<>();

        String easyTrack = "easyMode.mp3";
        String normalTrack = "normalMode.mp3";
        String hardTrack = "hardMode.mp3";
        String nightmareTrack = "nightmareMode.mp3";

        for (int i = 0; i < buttonLabels.length; i++) {
            Text buttonText = new Text(buttonLabels[i]);
            buttonText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
            buttonText.setFill(Color.WHITE);

            double centerX = getAppWidth() / 2 - buttonText.getLayoutBounds().getWidth() / 2;
            double centerY = getAppHeight() / 2 - buttonText.getLayoutBounds().getHeight() / 2 + (i * 50);
            buttonText.setTranslateX(centerX);
            buttonText.setTranslateY(centerY);

            // Add event listener to button
            buttonText.setOnMouseClicked(event -> {
                String buttonTextStr = ((Text) event.getSource()).getText();
                // Add code to handle button click here
                FXGL.getAudioPlayer().stopAllMusic();

                if (buttonTextStr.equals("Easy")) {
                    Music easy = FXGL.getAssetLoader().loadMusic(easyTrack);
                    FXGL.getAudioPlayer().loopMusic(easy);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/Snow.png?alt=media&token=0213e269-de1b-4212-9ea5-8be1e67b9bc0", 1100, 600, false, true));
                } else if (buttonTextStr.equals("Normal")) {
                    Music normal = FXGL.getAssetLoader().loadMusic(normalTrack);
                    FXGL.getAudioPlayer().loopMusic(normal);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/Snow.png?alt=media&token=0213e269-de1b-4212-9ea5-8be1e67b9bc0", 1100, 600, false, true));

                } else if (buttonTextStr.equals("Hard")) {
                    Music hard = FXGL.getAssetLoader().loadMusic(hardTrack);
                    FXGL.getAudioPlayer().loopMusic(hard);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/desert.jpg?alt=media&token=ee7446fc-c63a-4464-b653-8a48e48af8d2", 1100, 600, false, true));

                } else if (buttonTextStr.equals("Nightmare")) {
                    Music nightmare = FXGL.getAssetLoader().loadMusic(nightmareTrack);
                    FXGL.getAudioPlayer().loopMusic(nightmare);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/Snow.png?alt=media&token=0213e269-de1b-4212-9ea5-8be1e67b9bc0", 1100, 600, false, true));

                }
            });

            buttonList.add(buttonText);
        }

        Group buttonGroup = new Group();
        buttonGroup.getChildren().addAll(buttonList);
        containerGroup.getChildren().add(buttonGroup);
    }



    @Override
    protected void initUI() {
        String homeScreenSong = new String("Intro_mp3.mp3");
        Music homeScreenMusic = FXGL.getAssetLoader().loadMusic(homeScreenSong);
        FXGL.getAudioPlayer().loopMusic(homeScreenMusic);


        // Create a rectangle for the homescreen background
        Rectangle homeScreen = new Rectangle(0, 0, getAppWidth(), getAppHeight());
        homeScreen.setFill(Color.BLACK);

        // Create a text for the "Start" button
        Text startText = new Text("Start");
        startText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        startText.setFill(Color.WHITE);

        // Center the "Start" button on the homescreen
        double centerX = getAppWidth() / 2 - startText.getLayoutBounds().getWidth() / 2;
        double centerY = getAppHeight() / 2 - startText.getLayoutBounds().getHeight() /  0.5;
        startText.setTranslateX(centerX);
        startText.setTranslateY(centerY);

        // Create a group to hold the homescreen elements
        Group homeScreenGroup = new Group(homeScreen, startText);
        String[] buttonLabels = {"Easy", "Normal", "Hard", "Nightmare"};
        createButtons(buttonLabels, homeScreenGroup);


        // Add the homescreen group to the game scene
        getGameScene().addUINode(homeScreenGroup);

        // Transition to the current screen upon pressing the "Start" button
        startText.setOnMouseClicked(event -> {
            mediaPlayer.stop();
            getGameScene().removeUINode(homeScreenGroup);
            secondMediaPlayer.setAutoPlay(true);
            //getGameWorld().addEntities(playerentity, player2entity, ball);
        });

        // Set the game scene background
    }

    private void initScreenBounds() {
        Entity walls = entityBuilder()
                .type(EntityType.WALL)
                .collidable()
                .buildScreenBounds(150);

        getGameWorld().addEntity(walls);
    }

   @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
        level1.wallSwap();
    }


    public static void main(String[] args) {
        launch(args);
    }
}