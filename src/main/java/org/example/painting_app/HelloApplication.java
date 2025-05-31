package org.example.painting_app;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static ToolManager toolManager;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(500, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setLayoutX(150);
        canvas.setLayoutY(50);

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
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        createSliders(root);
        createToolPicker(root);
        createColorPicker(root);

        stage.setTitle("Paint <3");
        stage.setScene(scene);
        stage.show();
    }

    public static void createToolPicker(Group root) {
        ToggleGroup group = new ToggleGroup();

        RadioButton penButton = new RadioButton("Pen");
        penButton.setLayoutX(10);
        penButton.setLayoutY(180);
        root.getChildren().add(penButton);

        penButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (penButton.isSelected()) {
                toolManager.setActiveTool(toolManager.getPen());
            }
        });

        penButton.setToggleGroup(group);
        penButton.setSelected(true);

        RadioButton eraserButton = new RadioButton("Eraser");
        eraserButton.setLayoutX(10);
        eraserButton.setLayoutY(210);
        root.getChildren().add(eraserButton);

        eraserButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (eraserButton.isSelected()) {
                toolManager.setActiveTool(toolManager.getEraser());
            }
        });

        eraserButton.setToggleGroup(group);
    }

    public static void createColorPicker(Group root) {
        Button redButton = new Button();
        root.getChildren().add(redButton);
        redButton.setOnAction(e -> toolManager.setColor(Color.RED));
        redButton.setLayoutX(10);
        redButton.setLayoutY(15);

        Button greenButton = new Button();
        root.getChildren().add(greenButton);
        greenButton.setOnAction(e -> toolManager.setColor(Color.GREEN));
        greenButton.setLayoutX(40);
        greenButton.setLayoutY(50);

        Button blackButton = new Button();
        root.getChildren().add(blackButton);
        blackButton.setOnAction(e -> toolManager.setColor(Color.BLACK));
        blackButton.setLayoutX(40);
        blackButton.setLayoutY(120);

        Button blueButton = new Button();
        root.getChildren().add(blueButton);
        blueButton.setOnAction(e -> toolManager.setColor(Color.BLUE));
        blueButton.setLayoutX(10);
        blueButton.setLayoutY(85);

        Button yellowButton = new Button();
        root.getChildren().add(yellowButton);
        yellowButton.setOnAction(e -> toolManager.setColor(Color.YELLOW));
        yellowButton.setLayoutX(10);
        yellowButton.setLayoutY(50);

        Button pinkButton = new Button();
        root.getChildren().add(pinkButton);
        pinkButton.setOnAction(e -> toolManager.setColor(Color.PINK));
        pinkButton.setLayoutX(10);
        pinkButton.setLayoutY(120);

        Button purpleButton = new Button();
        root.getChildren().add(purpleButton);
        purpleButton.setOnAction(e -> toolManager.setColor(Color.PURPLE));
        purpleButton.setLayoutX(40);
        purpleButton.setLayoutY(85);

        Button orangeButton = new Button();
        root.getChildren().add(orangeButton);
        orangeButton.setOnAction(e -> toolManager.setColor(Color.ORANGE));
        orangeButton.setLayoutX(40);
        orangeButton.setLayoutY(15);
    }

    public static void createSliders(Group root) {
        Slider sizeSlider = new Slider(0, 100, 10);
        root.getChildren().add(sizeSlider);
        toolManager.setSizeSlider(sizeSlider);
        sizeSlider.setShowTickMarks(true);

        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                toolManager.setSize(newValue.doubleValue());
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}