module org.example.painting_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.painting_app to javafx.fxml;
    exports org.example.painting_app;
}