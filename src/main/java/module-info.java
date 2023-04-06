module com.example.pinguin {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires java.desktop;
    requires com.google.gson;

    opens assets.textures;
    opens assets.sounds;
    opens assets.music;

    opens com.example.pinguin to javafx.fxml;
    exports com.example.pinguin;
    exports com.example.pinguin.Levels;
}