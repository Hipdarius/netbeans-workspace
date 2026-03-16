import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Beans;

public class DrawPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;

    private transient Game game;

    public DrawPanel() {
        initComponents();
        setBackground(Beans.isDesignTime() ? Color.WHITE : Color.BLACK);
        setPreferredSize(new Dimension(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT));
        setFocusable(true);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (Beans.isDesignTime()) {
            return;
        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (game != null) {
            game.draw(g);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
