package org.example.painting_app;

import javafx.scene.paint.Color;

public interface AbstractTool {

    void drawOnMousePressed(double x, double y);

    void drawOnMouseDragged(double x, double y);

    void setSize(double size);
}
