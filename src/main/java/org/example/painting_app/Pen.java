package org.example.painting_app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pen implements AbstractTool {
    private double size;
    private GraphicsContext gc;

    public Pen(Color color, double size, GraphicsContext gc) {
        this.gc = gc;
        this.size = size;
        this.gc.setLineWidth(this.size);
    }

    public Pen(double size, GraphicsContext gc) {
        this(Color.BLACK, size, gc);
    }

    public void drawOnMousePressed(double x, double y) {
        gc.beginPath();
        gc.moveTo(x, y);
        gc.fillOval(x - size / 2.,y - size / 2.,size, size);
    }

    public void drawOnMouseDragged(double x, double y) {
        gc.lineTo(x, y);
        gc.stroke();
        gc.setLineWidth(size);
    }

    public void setSize(double size) {
        this.size = size;
        gc.setLineWidth(size);
    }
}
