package org.example.painting_app;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.embed.swing.SwingFXUtils;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    public static ToolManager toolManager;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(500, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setLayoutX(150);
        canvas.setLayoutY(50);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

        toolManager = new ToolManager(canvas);

        canvas.setOnMousePressed(e -> {
            toolManager.getActiveTool().drawOnMousePressed(e.getX(), e.getY());
        });

        canvas.setOnMouseDragged(e -> {
            toolManager.getActiveTool().drawOnMouseDragged(e.getX(), e.getY());
        });

        //Image brushTexture = new Image("C:/Users/dixxa/Desktop/java programms/painting_app/src/main/resources/images/eraser.png");
        //gc.setStroke(new ImagePattern(brushTexture));

        Image bgImage = new Image(HelloApplication.class.getResource("/images/background.jpg").toExternalForm());
        ImageView bgView = new ImageView(bgImage);
        bgView.setMouseTransparent(true);

        Image frameImage = new Image(HelloApplication.class.getResource("/images/frame.png").toExternalForm());
        ImageView frameView = new ImageView(frameImage);
        frameView.setX(125);
        frameView.setY(25);
        frameView.setMouseTransparent(true);

        Group root = new Group(bgView, canvas, frameView);
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        Image cursorImage = new Image(getClass().getResource("/images/cursor.png").toExternalForm());
        scene.setCursor(new ImageCursor(cursorImage,23,5));

        createSliders(root);
        createToolPicker(root);
        createColorPicker(root);
        createSaver(root, canvas, stage);
        createUploader(root, gc, stage);

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

        RadioButton rectangleButton = new RadioButton("Rectangle");
        rectangleButton.setLayoutX(10);
        rectangleButton.setLayoutY(250);
        root.getChildren().add(rectangleButton);

        rectangleButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (rectangleButton.isSelected()) {
                toolManager.setActiveTool(toolManager.getRectangle());
            }
        });

        rectangleButton.setToggleGroup(group);

        RadioButton softBrushButton = new RadioButton("Soft Brush");
        softBrushButton.setLayoutX(10);
        softBrushButton.setLayoutY(240);
        root.getChildren().add(softBrushButton);

        softBrushButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (softBrushButton.isSelected()) {
                toolManager.setActiveTool(toolManager.getBrush());
            }
        });

        softBrushButton.setToggleGroup(group);

    }

    public static void createColorPicker(Group root) {
        ToggleGroup group = new ToggleGroup();

        RadioButton redButton = new RadioButton();
        redButton.getStyleClass().add("red-button");
        root.getChildren().add(redButton);
        redButton.setOnAction(e -> toolManager.setColor(Color.rgb(246, 83, 83)));
        redButton.setLayoutX(10);
        redButton.setLayoutY(15);
        redButton.setToggleGroup(group);

        RadioButton greenButton = new RadioButton();
        greenButton.getStyleClass().add("green-button");
        root.getChildren().add(greenButton);
        greenButton.setOnAction(e -> toolManager.setColor(Color.rgb(145,202,87)));
        greenButton.setLayoutX(40);
        greenButton.setLayoutY(50);
        greenButton.setToggleGroup(group);

        RadioButton blackButton = new RadioButton();
        blackButton.getStyleClass().add("black-button");
        root.getChildren().add(blackButton);
        blackButton.setOnAction(e -> toolManager.setColor(Color.rgb(54,54,54)));
        blackButton.setLayoutX(40);
        blackButton.setLayoutY(120);
        blackButton.setToggleGroup(group);
        blackButton.setSelected(true);

        RadioButton blueButton = new RadioButton();
        blueButton.getStyleClass().add("blue-button");
        root.getChildren().add(blueButton);
        blueButton.setOnAction(e -> toolManager.setColor(Color.rgb(101,192,230)));
        blueButton.setLayoutX(10);
        blueButton.setLayoutY(85);
        blueButton.setToggleGroup(group);

        RadioButton yellowButton = new RadioButton();
        yellowButton.getStyleClass().add("yellow-button");
        root.getChildren().add(yellowButton);
        yellowButton.setOnAction(e -> toolManager.setColor(Color.rgb(250,242,96)));
        yellowButton.setLayoutX(10);
        yellowButton.setLayoutY(50);
        yellowButton.setToggleGroup(group);

        RadioButton pinkButton = new RadioButton();
        pinkButton.getStyleClass().add("pink-button");
        root.getChildren().add(pinkButton);
        pinkButton.setOnAction(e -> toolManager.setColor(Color.rgb(255,169,186)));
        pinkButton.setLayoutX(10);
        pinkButton.setLayoutY(120);
        pinkButton.setToggleGroup(group);

        RadioButton purpleButton = new RadioButton();
        purpleButton.getStyleClass().add("purple-button");
        root.getChildren().add(purpleButton);
        purpleButton.setOnAction(e -> toolManager.setColor(Color.rgb(170,125,224)));
        purpleButton.setLayoutX(40);
        purpleButton.setLayoutY(85);
        purpleButton.setToggleGroup(group);

        RadioButton orangeButton = new RadioButton();
        orangeButton.getStyleClass().add("orange-button");
        root.getChildren().add(orangeButton);
        orangeButton.setOnAction(e -> toolManager.setColor(Color.rgb(252,181,92)));
        orangeButton.setLayoutX(40);
        orangeButton.setLayoutY(15);
        orangeButton.setToggleGroup(group);
    }

    public static void createSliders(Group root) {
        Slider sizeSlider = new Slider(0, 100, 10);
        sizeSlider.setOrientation(Orientation.VERTICAL);
        root.getChildren().add(sizeSlider);
        toolManager.setSizeSlider(sizeSlider);
        sizeSlider.setShowTickMarks(true);

        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                toolManager.setSize(newValue.doubleValue());
            }
        });
    }

    public static void createSaver(Group root, Canvas canvas, Stage stage) {
        Button saveButton = new Button("Save");
        root.getChildren().add(saveButton);
        saveButton.setLayoutX(100);
        saveButton.setLayoutY(100);

        saveButton.setOnAction((e)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(500, 400);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
        });
    }

    public static void createUploader(Group root, GraphicsContext gc, Stage stage) {
        Button uploadButton = new Button("Upload");
        root.getChildren().add(uploadButton);
        uploadButton.setLayoutX(150);
        uploadButton.setLayoutY(150);

        uploadButton.setOnAction((e)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                Image image = new Image(file.toURI().toString());
                gc.drawImage(image, 0, 0, 500, 400);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}