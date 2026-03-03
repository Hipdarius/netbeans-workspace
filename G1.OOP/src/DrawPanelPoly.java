import java.awt.Color;
import java.awt.Graphics;

public class DrawPanelPoly extends javax.swing.JPanel {
    private ShapeList<Shape> shapes = null;

    public DrawPanelPoly() {
        setBackground(Color.WHITE);
    }

    public void setShapes(ShapeList<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shapes != null) {
            shapes.draw(g);
        }
    }
}
