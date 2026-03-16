import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private static final int INVADER_COUNT = 10;

    private final Player player;
    private Missile missile;
    private final ArrayList<Invader> allInvaders;
    private final int gameHeight;

    public Game(int width, int height) {
        gameHeight = height;
        player = new Player(0, Math.max(0, height - 30));
        missile = null;
        allInvaders = new ArrayList<>();

        int maxInvaderX = Math.max(0, width - 30);
        int maxInvaderY = Math.max(0, height / 2 - 30);
        for (int i = 0; i < INVADER_COUNT; i++) {
            int x = ThreadLocalRandom.current().nextInt(maxInvaderX + 1);
            int y = ThreadLocalRandom.current().nextInt(maxInvaderY + 1);
            allInvaders.add(new Invader(x, y));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Missile getMissile() {
        return missile;
    }

    public ArrayList<Invader> getAllInvaders() {
        return allInvaders;
    }

    public void draw(Graphics g) {
        for (Invader invader : allInvaders) {
            invader.draw(g);
        }

        player.draw(g);

        if (missile != null) {
            missile.draw(g);
        }
    }

    public void move(int width, int height) {
        for (Invader invader : allInvaders) {
            invader.moveWithinBounds(width, height);
        }

        if (missile != null) {
            missile.move();
            if (missile.y + missile.height < 0 || missile.y > height || missile.x + missile.width < 0 || missile.x > width) {
                missile = null;
            }
        }

        if (missile == null) {
            return;
        }

        for (int i = 0; i < allInvaders.size(); i++) {
            if (missile.intersects(allInvaders.get(i))) {
                allInvaders.remove(i);
                missile = null;
                break;
            }
        }
    }

    public void setPlayer(int x, int width) {
        int newX = x - player.width / 2;
        newX = Math.max(0, Math.min(newX, width - player.width));
        player.x = newX;
        player.y = Math.max(0, gameHeight - player.height);
    }

    public boolean isOver() {
        return allInvaders.isEmpty();
    }

    public void launchMissile() {
        if (missile != null) {
            return;
        }

        int missileX = player.x + player.width / 2 - 5 / 2;
        int missileY = player.y - 10;
        missile = new Missile(missileX, missileY);
    }
}

