
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author james
 */
public class Button implements Render{
    
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color color;
    
    public Button(int x, int y, int w, int h, Color c) {
        setxPos(x);
        setyPos(y);
        setWidth(w);
        setHeight(h);
        color = c;
    }
    
    public boolean isClicked(int x, int y) {
        return x > xPos && x < xPos + width && y > yPos && y > yPos + height;
    }
    
    @Override
    public void render(Canvas canvas) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(color);
        g.fillRect(xPos, yPos, width, height);
    }
    
    public void setxPos(int x) {
        xPos = x;
    }

    public void setyPos(int y) {
        yPos = y;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
