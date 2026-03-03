import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;

public class MainFramePoly extends javax.swing.JFrame {
    private final ShapeList<Shape> shapes = new ShapeList<>();
    private Line newLine = null;
    private Rectangle newRec = null;
    private Color oldColor = Color.BLACK;
    private int buttonPressed;

    private DrawPanelPoly drawPanel;
    private javax.swing.JButton changeColorButton;

    public MainFramePoly() {
        initComponents();
        setTitle("G1.OOP - Polymorphic Shapes");
        drawPanel.setShapes(shapes);
    }

    private void initComponents() {
        drawPanel = new DrawPanelPoly();
        changeColorButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        drawPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                drawPanelMouseDragged(evt);
            }
        });
        drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawPanelMousePressed(evt);
            }
        });

        changeColorButton.setText("change color");
        changeColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeColorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawPanelLayout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addComponent(changeColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawPanelLayout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addComponent(changeColorButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void drawPanelMousePressed(java.awt.event.MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            newLine = new Line(evt.getPoint(), evt.getPoint(), oldColor);
            shapes.add(newLine);
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            newRec = new Rectangle(evt.getPoint(), evt.getPoint(), oldColor);
            shapes.add(newRec);
        } else {
            return;
        }
        buttonPressed = evt.getButton();
        drawPanel.repaint();
    }

    private void drawPanelMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            shapes.clear();
        }
        drawPanel.repaint();
    }

    private void drawPanelMouseDragged(java.awt.event.MouseEvent evt) {
        if (buttonPressed == 1 && newLine != null) {
            newLine.setTo(evt.getPoint());
        } else if (buttonPressed == 3 && newRec != null) {
            newRec.setTo(evt.getPoint());
        } else {
            return;
        }
        drawPanel.repaint();
    }

    private void changeColorButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Color newColor = JColorChooser.showDialog(this, "Choix d'une couleur", oldColor);
        if (newColor != null) {
            oldColor = newColor;
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFramePoly().setVisible(true);
            }
        });
    }
}
