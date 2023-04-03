module com.example.pinguin {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.pinguin to javafx.fxml;
    exports com.example.pinguin;
}