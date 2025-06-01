package org.example.painting_app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Rectangle implements AbstractTool {
    private double size;
    private Canvas canvas;
    private GraphicsContext gc;
    private double startX, startY, endX, endY;
    private WritableImage buffer;

    public Rectangle(double size, Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        this.size = size;
    }

    public void drawOnMousePressed(double x, double y) {
        startX = x;
        startY = y;

        buffer = new WritableImage(500, 400);
        canvas.snapshot(null, buffer);
    }

    public void drawOnMouseDragged(double x, double y) {
        endX = x;
        endY = y;

        gc.clearRect(0, 0, 500, 400);
        gc.drawImage(buffer, 0, 0);
        drawSelectionBox();
    }

    private void drawSelectionBox() {
        double x = Math.min(startX, endX);
        double y = Math.min(startY, endY);
        double w = Math.abs(endX - startX);
        double h = Math.abs(endY - startY);
        gc.setLineWidth(size);
        gc.strokeRect(x, y, w, h);
    }

    public void setSize(double size){
        this.size = size;
    }

    public double getSize(){
        return size;
    }
}
