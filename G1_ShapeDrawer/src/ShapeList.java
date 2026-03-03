import java.awt.Graphics;
import java.util.ArrayList;

public class ShapeList<T extends Shape> {
    private final ArrayList<T> items = new ArrayList<>();

    public void add(T shape) {
        items.add(shape);
    }

    public void clear() {
        items.clear();
    }

    public void draw(Graphics g) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(g);
        }
    }
}
