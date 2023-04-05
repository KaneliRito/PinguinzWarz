package com.example.pinguin;



import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

import com.almasb.fxgl.physics.CollisionHandler;
import com.example.pinguin.Levels.Level2;
import javafx.scene.control.Label;

import com.example.pinguin.Levels.Level1;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.Group;
import com.almasb.fxgl.audio.Music;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import static com.almasb.fxgl.dsl.FXGL.*;


public class GameMap extends GameApplication {

    private Entity playerentity;
    private Entity player2entity;
    private PlayerComponent player;
    private PlayerComponent player2;
    private User activeUser;
    private final ReadFile reader = new ReadFile();
    private final WriteFile writer = new WriteFile();
    private Level1 level1;
    private Level2 level2;
    //private InputController inputController;

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("PinguinzWarz");
        settings.setVersion("0.1");
        level1 = new Level1();
        level2 = new Level2();

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



    protected void createGame() {

        getGameWorld().addEntityFactory(new GameEntityFactory());

        playerentity = spawn("player", 100, 100);
        player2entity = spawn("player2", 950, 100);
        player = playerentity.getComponent(PlayerComponent.class);
        player2 = player2entity.getComponent(PlayerComponent.class);
//


        initScreenBounds();


    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.WALL) {
            @Override
            protected void onCollision(Entity ball, Entity wall) {
                inc("player1score", +1);

            }
        });

    }

    private void createButtons(String[] buttonLabels, Group containerGroup) {
        Label text = new Label("Score player1: ");
        text.setStyle("-fx-text-fill: white");
        text.setTranslateX(10);
        text.setTranslateY(50);
        text.setFont(new Font(27));
        Label score = new Label("0");
        score.setStyle("-fx-text-fill: white");
        score.setTranslateX(180);
        score.setTranslateY(50);
        score.setFont(new Font(27));



        score.textProperty().bind(getWorldProperties().intProperty("player1score").asString());
        List<Text> buttonList = new ArrayList<>();

        String easyTrack = "easyMode.mp3";
        String normalTrack = "normalMode.mp3";
        String hardTrack = "hardMode.mp3";
        String nightmareTrack = "nightmareMode.mp3";

        // Define the color of the "Nightmare" button
        final Color NIGHTMARE_BUTTON_COLOR = Color.RED;

        for (int i = 0; i < buttonLabels.length; i++) {
            Text buttonText = new Text(buttonLabels[i]);
            buttonText.setFont(Font.font("Impact", FontWeight.BOLD, 50));

            if (buttonLabels[i].equals("Nightmare")) {
                buttonText.setFill(NIGHTMARE_BUTTON_COLOR);
            } else {
                buttonText.setFill(Color.WHITE);
            }

            double centerX = getAppWidth() / 2 - buttonText.getLayoutBounds().getWidth() / 2;
            double centerY = getAppHeight() / 2 - buttonText.getLayoutBounds().getHeight() / 2 + (i * 50);
            buttonText.setTranslateX(centerX);
            buttonText.setTranslateY(centerY);

            // Add event listener to button
            buttonText.setOnMouseClicked(event -> {
                createGame();
                String buttonTextStr = ((Text) event.getSource()).getText();
                // Add code to handle button click here
                FXGL.getAudioPlayer().stopAllMusic();
                getGameScene().addUINode(text);
                getGameScene().addUINode(score);

                if (buttonTextStr.equals("Easy")) {
                    Music easy = FXGL.getAssetLoader().loadMusic(easyTrack);
                    FXGL.getAudioPlayer().loopMusic(easy);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/Snow.png?alt=media&token=0213e269-de1b-4212-9ea5-8be1e67b9bc0", 1100, 600, false, true));
                    level1.spawnEntity();
                } else if (buttonTextStr.equals("Normal")) {
                    Music normal = FXGL.getAssetLoader().loadMusic(normalTrack);
                    FXGL.getAudioPlayer().loopMusic(normal);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/desert1.jpg?alt=media&token=a3f398b9-aa7d-4d1f-a0ce-2b3092e2a185", 1100, 600, false, true));
                    level2.spawnEntity();
                } else if (buttonTextStr.equals("Hard")) {
                    Music hard = FXGL.getAssetLoader().loadMusic(hardTrack);
                    FXGL.getAudioPlayer().loopMusic(hard);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/desert.jpg?alt=media&token=ee7446fc-c63a-4464-b653-8a48e48af8d2", 1100, 600, false, true));
                    level2.spawnEntity();
                } else if (buttonTextStr.equals("Nightmare")) {
                    Music nightmare = FXGL.getAssetLoader().loadMusic(nightmareTrack);
                    FXGL.getAudioPlayer().loopMusic(nightmare);
                    getGameScene().removeUINode(containerGroup);
                    getGameScene().setBackgroundRepeat(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/stones.png?alt=media&token=a0a02a75-b79a-46db-951a-bfcdb5fb1319", 1100, 600, false, true));
                    level2.spawnEntity();
                }

                // Add code to start the game here
            });


            buttonList.add(buttonText);
        }

        containerGroup.getChildren().addAll(buttonList);
    }

    @Override
    protected void initUI() {
            //Login Screen
            GridPane root = new GridPane();
            root.setBackground(new Background(new BackgroundImage(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/homescreen_background1.jpg?alt=media&token=7575cd09-584f-4727-97b0-a2194982346e", 1100, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

            root.setMinWidth(1100);
            root.setMinHeight(600);
            root.setHgap(10);
            root.setVgap(5);
            root.setAlignment(Pos.CENTER);

            ColumnConstraints col1 = new ColumnConstraints();
            col1.setMinWidth(125);
            root.getColumnConstraints().add(col1);

            Text loginText = new Text("Log in");
            loginText.setFont(new Font("Impact", 30));
            loginText.setFill(Color.YELLOW);


            Text userText = new Text("Username: ");
            TextField userInput = new TextField();
            userText.setFont(new Font("Impact", 30));
            userText.setFill(Color.YELLOW);

            Text passwordText = new Text("Password: ");
            TextField passwordInput = new TextField();
            passwordText.setFont(new Font("Impact", 30));
            passwordText.setFill(Color.YELLOW);

            Button cancelButton = new Button("Cancel");
            Button submitButton = new Button("Submit");


            //        mainUi controller = new mainUi();
//        UI ui = getAssetLoader().loadUI("main.fxml", controller);
//        controller.getLabelScoreP1().textProperty().bind(getip("player1score").asString());
//        controller.getLabelScoreP2().textProperty().bind(getip("player2score").asString());
//        getGameScene().addUI(ui);




        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<>()  {
                    @Override
                    public void handle(MouseEvent e) {
                        try {
                            Platform.exit();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<>() {
                    @Override
                    public void handle(MouseEvent e) {
                        try {
                            String username = userInput.getText();
                            String password = passwordInput.getText();

                            activeUser = new User(username, password);

                            switch (reader.checkUsers(username, password, loginText)) {
                                case 1 -> {
                                    writer.addText(reader.readFile(), username, password);
                                    setLevelScreen(root);

                                }
                                case 2 -> {
                                    setLevelScreen(root);

                                }
                                case 3 -> {
                                    userInput.setText("");
                                    passwordInput.setText("");
                                }
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }) ;

        root.add(loginText, 0, 0);
        root.add(userText, 0, 1);
        root.add(userInput, 1, 1);
        root.add(passwordText, 0, 2);
        root.add(passwordInput, 1, 2);
        root.add(cancelButton, 0, 3);
        root.add(submitButton, 1, 3);

        getGameScene().addUINode(root);
    }
    public void setLevelScreen(Pane root) {
        getGameScene().removeUINode(root);

        String homeScreenSong = new String("Intro_mp3.mp3");
        Music homeScreenMusic = FXGL.getAssetLoader().loadMusic(homeScreenSong);
        FXGL.getAudioPlayer().loopMusic(homeScreenMusic);


        // Create a rectangle for the homescreen background
        ImageView homeScreen = new ImageView(new Image("https://firebasestorage.googleapis.com/v0/b/iprop-games-database.appspot.com/o/homescreen_background1.jpg?alt=media&token=7575cd09-584f-4727-97b0-a2194982346e", getAppWidth(), getAppHeight(), false, true));

        // Create a text for the "Start" button

        Text settings = new Text("Settings");
        settings.setFont(Font.font("Impact", FontWeight.BOLD, 50));
        settings.setFill(Color.YELLOW);


        // Center the "Start" button on the homescreen
        double centerX = getAppWidth() / 10 - settings.getLayoutBounds().getWidth() / 3;
        double centerY = getAppHeight() / 3 - settings.getLayoutBounds().getHeight() / 0.5;
        settings.setTranslateX(centerX);
        settings.setTranslateY(centerY);

        // Create a group to hold the homescreen elements
        Group homeScreenGroup = new Group(homeScreen, settings);
        String[] buttonLabels = {"Easy", "Normal", "Hard", "Nightmare"};
        createButtons(buttonLabels, homeScreenGroup);

        settings.setOnMouseClicked(event -> {
            // Simulate a key press of the Escape key
            KeyEvent keyEvent = new KeyEvent(
                    KeyEvent.KEY_PRESSED, // event type
                    "", // character
                    "", // text
                    KeyCode.ESCAPE, // code
                    false, // shift down
                    false, // control down
                    false, // alt down
                    false // meta down
            );
            settings.fireEvent(keyEvent);
        });


        // Add the homescreen group to the game scene
        getGameScene().addUINode(homeScreenGroup);




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