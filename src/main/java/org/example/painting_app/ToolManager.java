package org.example.painting_app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class ToolManager {
    private GraphicsContext gc;

    private Pen pen;
    private Eraser eraser;
    private SoftBrush brush;
    private Rectangle rectangle;
    private AbstractTool activeTool;
    private Color color;
    private Slider sizeSlider;

    public ToolManager(Canvas canvas) {
        this.gc = canvas.getGraphicsContext2D();
        pen = new Pen(3, gc);
        eraser = new Eraser(3, gc);
        brush = new SoftBrush(3, gc);
        rectangle = new Rectangle(3, canvas);
        activeTool = pen;
        color = Color.BLACK;
    }

    public Pen getPen() {
        return pen;
    }

    public Eraser getEraser() {
        return eraser;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public SoftBrush getBrush() { return brush; }

    public AbstractTool getActiveTool(){
        return activeTool;
    }

    public void setColor(Color newColor) {
        color = newColor;
        gc.setFill(color);
        gc.setStroke(color);
    }

    public void setSize(double newSize) {
        activeTool.setSize(newSize);
    }

    public void setActiveTool(AbstractTool newActiveTool) {
        activeTool = newActiveTool;
        gc.setFill(color);
        gc.setStroke(color);
        sizeSlider.setValue(activeTool.getSize());
    }

    public void setSizeSlider(Slider newSizeSlider) {
        sizeSlider = newSizeSlider;
    }
}
