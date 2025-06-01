package org.example.painting_app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SoftBrush implements AbstractTool {
    private double size;
    private GraphicsContext gc;
    private double lastX, lastY;
    private double blurRadius; // Радиус размытия краев

    public SoftBrush(double size, GraphicsContext gc) {
        this.gc = gc;
        this.size = size;
        this.blurRadius = size * 0.5; // Размытие пропорционально размеру
    }

    public void drawOnMousePressed(double x, double y) {
        lastX = x;
        lastY = y;
        drawSoftPoint(x, y);
    }

    public void drawOnMouseDragged(double x, double y) {
        double distance = Math.hypot(x - lastX, y - lastY);
        double steps = Math.max(distance, 1);

        for (double i = 0; i <= steps; i++) {
            double t = i / steps;
            double currX = lastX + t * (x - lastX);
            double currY = lastY + t * (y - lastY);
            drawSoftPoint(currX, currY);
        }

        lastX = x;
        lastY = y;
    }

    private void drawSoftPoint(double x, double y) {
        Color baseColor = (Color) gc.getFill();
        double centerRadius = size / 2.0;
        double fullRadius = centerRadius + blurRadius;

        // настройки прозрачности
        double baseOpacity = 0.2;
        double fadeOut = 0.5;

        double distanceToEnd = Math.hypot(x - lastX, y - lastY);
        double fadeFactor = Math.min(1.0, distanceToEnd / (size * fadeOut));

        // общая прозрачность с учетом затухания на концах
        double opacity = baseOpacity * fadeFactor;

        for (double r = 0; r <= fullRadius; r += 0.5) {
            double currentOpacity;
            if (r <= centerRadius) {
                currentOpacity = opacity;
            } else {
                currentOpacity = opacity * (1 - (r - centerRadius) / blurRadius);
            }

            if (currentOpacity > 0) {
                Color colorWithOpacity = Color.color(
                        baseColor.getRed(),
                        baseColor.getGreen(),
                        baseColor.getBlue(),
                        currentOpacity * baseColor.getOpacity()
                );
                gc.setFill(colorWithOpacity);
                gc.fillOval(x - r, y - r, r * 2, r * 2);
            }
        }

        gc.setFill(baseColor);
    }

    public void setSize(double size) {
        this.size = size;
        this.blurRadius = size * 0.5; // обновление радиуса размытия при изменении размера
    }

    public double getSize() {
        return size;
    }
}
