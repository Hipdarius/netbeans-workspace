import java.awt.Graphics;
import java.util.ArrayList;

public class ShapeList<T extends Shape> {
    protected ArrayList<T> items = new ArrayList<>();

    public void draw(Graphics g) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(g);
        }
    }

    public void add(T e) {
        items.add(e);
    }

    public void clear() {
        items.clear();
    }
}
