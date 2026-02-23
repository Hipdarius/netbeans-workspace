
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phili
 */
public class Rectangles {
    private ArrayList<Rectangle> alRectangles = new ArrayList();
    
      public void draw(Graphics g) {
        for (int i = 0; i < alRectangles.size(); i++) {
            alRectangles.get(i).draw(g);
            
        }
    }
     public void add(Rectangle e) {
        alRectangles.add(e);
    }

    public void clear() {
        alRectangles.clear();
    }
}

