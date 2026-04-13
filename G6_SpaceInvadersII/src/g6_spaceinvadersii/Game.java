package g6_spaceinvadersii;

import java.awt.Graphics;
import java.util.ArrayList;

public class Game {

    private Player player;
    private Missile missile;
    private ArrayList<Invader> allInvaders;
    private ArrayList<Integer> allScores;

    private int invaderXSpeed = 1;
    private int invaderYSpeed = 10;
    private int invaderDirection = 1;
    private int missileSpeed = 5;

    private int score;
    private int width;
    private int height;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        allScores = new ArrayList<>();
        reset();
    }

    public Object[] toArray() {
        return allScores.toArray();
    }

    public void createInvaders() {
        allInvaders = new ArrayList<>();
        int cols = 8;
        int rows = 5;
        int spacingX = 50;
        int spacingY = 45;
        int startX = 20;
        int startY = 20;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                allInvaders.add(new Invader(startX + col * spacingX, startY + row * spacingY));
            }
        }
    }

    public void reset() {
        missile = null;
        score = 0;
        createInvaders();
        invaderDirection = 1;
        player = new Player(width / 2, height - 50);
    }

    public void sortScores() {
        for (int i = 0; i < allScores.size() - 1; i++) {
            for (int j = 0; j < allScores.size() - 1 - i; j++) {
                if (allScores.get(j) < allScores.get(j + 1)) {
                    int temp = allScores.get(j);
                    allScores.set(j, allScores.get(j + 1));
                    allScores.set(j + 1, temp);
                }
            }
        }
    }

    public int getScoresAverage() {
        if (allScores.isEmpty()) return 0;
        int sum = 0;
        for (int s : allScores) sum += s;
        return sum / allScores.size();
    }

    public int findFirstOutside() {
        for (int i = 0; i < allInvaders.size(); i++) {
            Invader invader = allInvaders.get(i);
            if (invader.getX() < 0 || invader.getX() + invader.getWidth() > width) {
                return i;
            }
        }
        return -1;
    }

    public void doStep() {
        for (Invader invader : allInvaders) {
            invader.setX(invader.getX() + invaderXSpeed * invaderDirection);
        }

        if (findFirstOutside() != -1) {
            invaderDirection = -invaderDirection;
            for (Invader invader : allInvaders) {
                invader.setX(invader.getX() + invaderXSpeed * invaderDirection * 2);
                invader.setY(invader.getY() + invaderYSpeed);
            }
        }

        if (missile != null) {
            missile.setY(missile.getY() - missileSpeed);
            if (missile.getY() + missile.getHeight() < 0) {
                missile = null;
            }
        }

        if (missile != null) {
            for (int i = 0; i < allInvaders.size(); i++) {
                if (allInvaders.get(i).collidesWith(missile)) {
                    allInvaders.remove(i);
                    missile = null;
                    score++;
                    break;
                }
            }
        }
    }

    public boolean checkGameOver() {
        if (allInvaders.isEmpty()) return true;
        for (Invader invader : allInvaders) {
            if (invader.getY() + invader.getHeight() >= player.getY()) return true;
        }
        return false;
    }

    public String getScoreText() {
        return "Score: " + score;
    }

    public void newMissile() {
        if (missile == null) {
            missile = new Missile(player.getX() + player.getWidth() / 2, player.getY());
        }
    }

    public void setPlayerXCenter(int xCenter) {
        player.setNewXCenter(xCenter, width);
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Integer> getAllScores() {
        return allScores;
    }

    public void draw(Graphics g) {
        player.draw(g);
        for (Invader inv : allInvaders) {
            inv.draw(g);
        }
        if (missile != null) {
            missile.draw(g);
        }
    }
}
