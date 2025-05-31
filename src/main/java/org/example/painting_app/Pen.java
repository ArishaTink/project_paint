package org.example.painting_app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pen implements AbstractTool {
    private double size;
    private GraphicsContext gc;

    private double lastX, lastY;

    public Pen(double size, GraphicsContext gc) {
        this.gc = gc;
        this.size = size;
        this.gc.setLineWidth(this.size);
    }

    public void drawOnMousePressed(double x, double y) {
        lastX = x;
        lastY = y;
        gc.fillOval(x - size / 2.,y - size / 2.,size, size);
    }

    public void drawOnMouseDragged(double x, double y) {
        double distance = Math.hypot(x - lastX, y - lastY);

        for (double i = 0; i <= distance; i++) {
            double t = i / distance;
            double currX = lastX + t * (x - lastX);
            double currY = lastY + t * (y - lastY);
            gc.fillOval(currX - size / 2.,currY - size / 2.,size, size);
        }

        lastX = x;
        lastY = y;
    }

    public void setSize(double size) {
        this.size = size;
        gc.setLineWidth(size);
    }

    public double getSize() {
        return size;
    }
}
