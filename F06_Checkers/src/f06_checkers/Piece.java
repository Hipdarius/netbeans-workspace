/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package f06_checkers;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Darius
 */
public class Piece {
    private Color color;
    private int col;
    private int row;
    
    public Piece(Color color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;
    }
    
    public void moveTo(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public Color getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public void draw(Graphics g, int squareSide) {
        g.setColor(this.color);
        
        int x = this.col * squareSide;
        int y = this.row * squareSide;
        
        g.fillOval(x + 4, y + 4, squareSide - 8, squareSide - 8);
    }
}
