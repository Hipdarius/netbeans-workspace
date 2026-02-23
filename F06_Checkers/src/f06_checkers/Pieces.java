/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package f06_checkers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Darius
 */
public class Pieces {
    private ArrayList<Piece> alPieces;
    
    public Pieces() {
        alPieces = new ArrayList();
    }
    
    public void init() {
        alPieces.clear();
        
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 8; col++) {
                if((row + col) % 2 != 0) {
                    alPieces.add(new Piece(Color.RED, col, row));
                }
            }
        }
        
        for(int row = 5; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if((row + col) % 2 != 0) {
                    alPieces.add(new Piece(Color.BLUE, col, row));
                }
            }
        }
    }
    
    public Piece getPieceAt(int col, int row) {
        for(Piece p : alPieces) {
            if(p.getCol() == col && p.getRow() == row) {
                return p;
            }
        }
        return null;
    }
    
    public boolean move(int startCol, int startRow, int destCol, int destRow) {
        Piece p = getPieceAt(startCol, startRow);
        
        if(p != null) {
            if(destCol < 0 || destCol > 7 || destRow < 0 || destRow > 7) {
                alPieces.remove(p);
                return true;
            }
            
            Piece target = getPieceAt(destCol, destRow);
            if (target == null) {
                p.moveTo(destCol, destRow);
                return true;
            }
        }
        return false;
    }
    
    public void draw(Graphics g, int width, int height) {
        int squareSide = Math.min(width, height) / 8;
        
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(col * squareSide, row * squareSide, squareSide, squareSide);
                
                g.setColor(Color.BLACK);
                g.drawRect(col * squareSide, row * squareSide, squareSide, squareSide);
            }
        }
        
        for(Piece p : alPieces) {
            p.draw(g, squareSide);
        }
    }
}
