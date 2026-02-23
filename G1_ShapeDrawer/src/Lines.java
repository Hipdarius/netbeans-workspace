
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phili
 */
public class Lines {
    
    private ArrayList<Line> alLines = new ArrayList();
    
    public void draw(Graphics g) {
        for (int i = 0; i < alLines.size(); i++) {
            alLines.get(i).draw(g);
            
        }
    }
     public void add(Line e) {
        alLines.add(e);
    }

    public void clear() {
        alLines.clear();
    }
}