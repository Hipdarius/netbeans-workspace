package javapede;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static final int GRID_W = Element.GRID_W;
    private static final int GRID_H = Element.GRID_H;

    private final ArrayList<Element> javapede;
    private final ArrayList<Mushroom> aMushrooms;
    private final Random rng;

    private Player player;
    private Bullet bullet;
    private int side;
    private int score;

    public Game(int canvasW, int canvasH) {
        side = Math.min(canvasW / GRID_W, canvasH / GRID_H);
        if (side < 5) {
            side = 5;
        }

        score = 0;
        rng = new Random();
        javapede = new ArrayList<>();
        aMushrooms = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            addRandomMushroom();
        }

        player = new Player(GRID_W / 2, GRID_H - 1);

        int headX = GRID_W / 2;
        int headY = 0;
        Element head = new Element(headX, headY, 1, Color.RED);
        javapede.add(head);
        for (int x = headX - 1; x >= headX - 11; x--) {
            Element body = new Element(x, headY, 1, Color.GREEN);
            javapede.add(body);
        }
    }

    public int getScore() {
        return score;
    }

    public void addRandomMushroom() {
        int x = rng.nextInt(GRID_W);
        int y = rng.nextInt(GRID_H - 2) + 1;
        aMushrooms.add(new Mushroom(x, y));
    }

    public Mushroom getMushroomAt(int x, int y) {
        for (Mushroom m : aMushrooms) {
            if (m.getX() == x && m.getY() == y) {
                return m;
            }
        }
        return null;
    }

    public int finalScoreAdjustment() {
        int max = 0;
        for (Element e : javapede) {
            if (e.getPoints() > max) {
                max = e.getPoints();
            }
        }
        return max;
    }

    public int checkJavapedeCollision(Element e) {
        for (int i = 0; i < javapede.size(); i++) {
            Element je = javapede.get(i);
            if (je.getX() == e.getX() && je.getY() == e.getY()) {
                return i;
            }
        }
        return -1;
    }

    public void manageJavapedeMushroomCollisions() {
        for (Mushroom m : aMushrooms) {
            for (Element e : javapede) {
                if (m.getX() == e.getX() && m.getY() == e.getY()) {
                    e.setDirection(-e.getDirection());
                    int nx = e.getX() + e.getDirection();
                    if (nx >= 0 && nx <= GRID_W - 1) {
                        e.setX(nx);
                    }
                    if (e.getY() < GRID_H - 1) {
                        e.setY(e.getY() + 1);
                    } else {
                        e.setY(GRID_H - 1);
                    }
                }
            }
        }
    }

    public boolean moveJavapede() {
        for (Element e : javapede) {
            e.move();
        }
        manageJavapedeMushroomCollisions();

        for (Element e : javapede) {
            if (e.getX() == player.getX() && e.getY() == player.getY()) {
                score -= finalScoreAdjustment();
                return false;
            }
        }
        return true;
    }

    public void movePlayer(int direction) {
        player.setDirection(direction);
        player.move();
    }

    public int moveBullet() {
        if (bullet == null) {
            return 0;
        }

        int bx = bullet.getX();
        int by = bullet.getY();

        Mushroom m = getMushroomAt(bx, by);
        if (m != null) {
            m.touch();
            score += m.getPoints();
            if (m.getHits() >= 3) {
                aMushrooms.remove(m);
            }
            bullet = null;
            return 0;
        }

        int hitIndex = checkJavapedeCollision(bullet);
        if (hitIndex >= 0) {
            if (!javapede.isEmpty()) {
                Element last = javapede.get(javapede.size() - 1);
                score += last.getPoints();
                javapede.remove(javapede.size() - 1);
            }
            bullet = null;
            if (javapede.isEmpty()) {
                return 2;
            }
            return 0;
        }

        if (by == 0) {
            bullet = null;
            return 0;
        }

        bullet.move();
        return 1;
    }

    public void shoot() {
        if (bullet != null) {
            return;
        }
        int x = player.getX();
        int y = player.getY() - 1;
        if (y >= 0) {
            bullet = new Bullet(x, y);
        }
    }

    public void draw(Graphics2D g2, int panelW, int panelH) {
        int areaW = GRID_W * side;
        int areaH = GRID_H * side;
        int offX = Math.max(0, (panelW - areaW) / 2);
        int offY = Math.max(0, (panelH - areaH) / 2);

        g2.setColor(Color.BLACK);
        g2.fillRect(offX, offY, areaW, areaH);

        for (Mushroom m : aMushrooms) {
            m.draw(g2, side, offX, offY);
        }
        for (Element e : javapede) {
            e.draw(g2, side, offX, offY);
        }
        if (player != null) {
            player.draw(g2, side, offX, offY);
        }
        if (bullet != null) {
            bullet.draw(g2, side, offX, offY);
        }
    }
}
