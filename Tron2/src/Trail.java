/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author james
 */
public class Trail {

    private ArrayList<Line> Lines;
    private int Count;

    public Trail() {
        Lines = new ArrayList<Line>();
    }

    public void createNextLine(Line l) {
        Lines.add(l);
    }
    
    public Line getLine(int i){
        return Lines.get(i);
    }
    
    public int getSize(){
        return Lines.size();
    }
}
