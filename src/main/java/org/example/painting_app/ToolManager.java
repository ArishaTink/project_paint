package org.example.painting_app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ToolManager {
    private GraphicsContext gc;

    private Pen pen;
    private Eraser eraser;
    private AbstractTool activeTool;
    private Color color;

    public ToolManager(GraphicsContext gc) {
        this.gc = gc;
        pen = new Pen(3, gc);
        eraser = new Eraser(3, gc);
        activeTool = pen;
        color = Color.BLACK;
    }

    public Pen getPen() {
        return pen;
    }

    public Eraser getEraser() {
        return eraser;
    }

    public AbstractTool getActiveTool(){
        return activeTool;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public void setActiveTool(AbstractTool newActiveTool) {
        activeTool = newActiveTool;
        if (newActiveTool.getClass() == Eraser.class) {
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.WHITE);
        } else {
            gc.setFill(color);
            gc.setStroke(color);
        }
    }
}
