
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Darius
 */
public class Asteroids {
    private ArrayList<Asteroid> asteroidAL = new ArrayList<>();
    private int selectedIndex = -1;
    private int nextNumber = 1;
    
    public Asteroid addNew() {
        Asteroid asteroid = new Asteroid(nextNumber);
        nextNumber++;
        asteroidAL.add(asteroid);
        selectedIndex = asteroidAL.size() - 1;
        return asteroid;
    }
    
    public void add(Asteroid asteroid) {
        asteroidAL.add(asteroid);
        selectedIndex = asteroidAL.size() - 1;
        
        if (asteroid.getNumber() >= nextNumber) {
            nextNumber = asteroid.getNumber() + 1;
        }
    }
    
    public Asteroid get(int index) {
        if (index < 0 || index >= asteroidAL.size()) {
            return null;
        }
        return asteroidAL.get(index);
    }
    
    public Asteroid getSelected() {
        return get(selectedIndex);
    }
    
    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    public void setSelectedIndex(int selectedIndex) {
        if (selectedIndex == -1) {
            this.selectedIndex = -1;
            return;
        }
        
        if (selectedIndex >= 0 && selectedIndex < asteroidAL.size()) {
            this.selectedIndex = selectedIndex;
        }
    }
    
    public int size() {
        return asteroidAL.size();
    }
    
    public void clear() {
        asteroidAL.clear();
        selectedIndex = -1;
        nextNumber = 1;
    }
    
    public void draw(Graphics g) {
        for (Asteroid asteroid : asteroidAL) {
            asteroid.draw(g);
        }
    }
    
    public void saveToFile(String fileName) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (Asteroid asteroid : asteroidAL) {
                out.println(asteroid.toFileString());
            }
        }
    }
    
    public void loadFromFile(String fileName) throws IOException {
        clear();
        
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    add(Asteroid.fromFileString(line));
                }
            }
        }
        
        if (asteroidAL.size() > 0) {
            selectedIndex = 0;
        }
    }
}
