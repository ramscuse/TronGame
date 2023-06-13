/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author james
 */
public class Line extends GameElement{
    public Line() {
        super();
    }
    
    public Line(int x, int y, int w, int h, Color color){
        super(x,y,w,h,color);
    }
    
    @Override
    public void render(Canvas canvas) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(getColor());
        graphics.fillRect(getxPos(), getyPos(), getWidth(), getHeight());
    }
    
}
