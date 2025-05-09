package org.example.painting_app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static ToolManager toolManager;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        toolManager = new ToolManager(gc);

        canvas.setOnMousePressed(e -> {
            toolManager.getActiveTool().drawOnMousePressed(e.getX(), e.getY());
        });

        canvas.setOnMouseDragged(e -> {
            toolManager.getActiveTool().drawOnMouseDragged(e.getX(), e.getY());
        });

        //Image brushTexture = new Image("C:/Users/dixxa/Desktop/java programms/painting_app/src/main/resources/images/eraser.png");
        //gc.setStroke(new ImagePattern(brushTexture));

        Group root = new Group(canvas);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        createInterface(root);

        stage.setTitle("Paint <3");
        stage.setScene(scene);
        stage.show();
    }

    public static void createInterface(Group root) {
        ToggleGroup group = new ToggleGroup();

        RadioButton penButton = new RadioButton("Pen");
        root.getChildren().add(penButton);

        penButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (penButton.isSelected()) {
                toolManager.setActiveTool(toolManager.getPen());
            }
        });

        penButton.setToggleGroup(group);
        penButton.setSelected(true);

        RadioButton eraserButton = new RadioButton("Eraser");
        eraserButton.setLayoutX(40);
        eraserButton.setLayoutY(40);
        root.getChildren().add(eraserButton);

        eraserButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (eraserButton.isSelected()) {
                toolManager.setActiveTool(toolManager.getEraser());
            }
        });

        eraserButton.setToggleGroup(group);
    }

    public static void main(String[] args) {
        launch();
    }
}