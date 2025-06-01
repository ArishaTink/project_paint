module org.example.painting_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens org.example.painting_app to javafx.fxml;
    exports org.example.painting_app;
}