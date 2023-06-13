
import java.awt.Graphics;
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
public abstract class GameElement implements Tick, Render {

    @Override
    public void tick() {
    }

    @Override
    public void render(Canvas canvas) {
    }
    
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color color;

    public GameElement() {
        xPos = 10;
        yPos = 10;
        width = 10;
        height = 10;
        color = Color.WHITE;
    }

    public GameElement(int x, int y, int w, int h, Color c) {
        setxPos(x);
        setyPos(y);
        setWidth(w);
        setHeight(h);
        setColor(c);
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

    public void setColor(Color x){
        color = x;
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

    public Color getColor(){
        return color;
    }
}
