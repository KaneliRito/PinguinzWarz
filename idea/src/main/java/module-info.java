module com.example.pinguin {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.example.pinguin to javafx.fxml;
    exports com.example.pinguin;
}