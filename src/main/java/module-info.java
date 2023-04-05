open module com.example.pinguin {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;

    //opens assets.textures;
    //opens assets.sounds;

    //opens com.example.pinguin to javafx.fxml;
    exports com.example.pinguin;
    exports com.example.pinguin.Levels;
}