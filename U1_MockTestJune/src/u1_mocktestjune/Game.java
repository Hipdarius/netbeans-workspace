package u1_mocktestjune;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Game {

    private int width;
    private int height;
    private MovingBall playerBall;
    private ArrayList<Ball> alBalls = new ArrayList<Ball>();

    public Game(int pWidth, int pHeight) {
        // Initialize the game size, the list of red balls, and the player ball.
    }

    public void addRandomBall() {
        // Add one red ball at a random valid position.
    }

    public void clearBalls() {
        // Remove all red balls from the list.
    }

    public int getBallCount() {
        // Return the number of red balls.
        return 0;
    }

    public void draw(Graphics g) {
        // Draw all red balls and the green player ball.
    }

    public void saveToTextFile(String fileName) throws FileNotFoundException, IOException {
        // Save every red ball as one line in the text file.
    }

    public void loadFromTextFile(String fileName) throws FileNotFoundException, IOException {
        // Load red balls from the file and reset the player ball.
    }
}
