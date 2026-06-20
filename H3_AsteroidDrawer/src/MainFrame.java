import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Darius
 */
public class MainFrame extends javax.swing.JFrame {
    private Asteroids asteroids = new Asteroids();
    private DefaultListModel<String> asteroidListModel = new DefaultListModel<>();
    private int movingPointIndex = -1;
    private boolean updatingAsteroidList = false;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setTitle("Asteroid Drawer");
        clearAsteroidButton.setText("Clear Asteroids");
        saveAsteroidButton.setText("Save Asteroids");
        loadAsteroidButton.setText("Load Asteroids");
        drawPanel.setAsteroids(asteroids);
        itemList.setModel(asteroidListModel);
        wireActions();
        newAsteroidButtonActionPerformed(null);
    }

    private void wireActions() {
        centerAsteroidButton.addActionListener((evt) -> centerSelectedAsteroid());
        clearAsteroidButton.addActionListener((evt) -> clearAsteroids());
        saveAsteroidButton.addActionListener((evt) -> saveAsteroids());
        loadAsteroidButton.addActionListener((evt) -> loadAsteroids());
        
        itemList.addListSelectionListener((evt) -> {
            if (!evt.getValueIsAdjusting()) {
                if (updatingAsteroidList) {
                    return;
                }
                asteroids.setSelectedIndex(itemList.getSelectedIndex());
                movingPointIndex = -1;
                drawPanel.repaint();
            }
        });
        
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                drawPanelMousePressed(evt);
            }
            
            @Override
            public void mouseReleased(MouseEvent evt) {
                movingPointIndex = -1;
            }
        });
        
        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                drawPanelMouseDragged(evt);
            }
        });
        
        drawPanel.addMouseWheelListener((MouseWheelEvent evt) -> {
            drawPanelMouseWheelMoved(evt);
        });
    }
    
    private Asteroid getSelectedAsteroid() {
        Asteroid asteroid = asteroids.getSelected();
        
        if (asteroid == null) {
            asteroid = asteroids.addNew();
            updateAsteroidList();
        }
        
        return asteroid;
    }
    
    private void updateAsteroidList() {
        int selectedIndex = asteroids.getSelectedIndex();
        updatingAsteroidList = true;
        asteroidListModel.clear();
        
        for (int i = 0; i < asteroids.size(); i++) {
            asteroidListModel.addElement(asteroids.get(i).toString());
        }
        updatingAsteroidList = false;
        
        if (selectedIndex >= 0) {
            itemList.setSelectedIndex(selectedIndex);
        } else {
            itemList.clearSelection();
        }
    }
    
    private void centerSelectedAsteroid() {
        Asteroid asteroid = asteroids.getSelected();
        
        if (asteroid != null) {
            asteroid.centerIn(drawPanel.getWidth(), drawPanel.getHeight());
            drawPanel.repaint();
        }
    }
    
    private void clearAsteroids() {
        asteroids.clear();
        asteroidListModel.clear();
        movingPointIndex = -1;
        drawPanel.repaint();
    }
    
    private void saveAsteroids() {
        String fileName = chooseFile(true);
        
        if (fileName == null) {
            return;
        }
        
        try {
            asteroids.saveToFile(fileName);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void loadAsteroids() {
        String fileName = chooseFile(false);
        
        if (fileName == null) {
            return;
        }
        
        try {
            asteroids.loadFromFile(fileName);
            updateAsteroidList();
            movingPointIndex = -1;
            drawPanel.repaint();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private String chooseFile(boolean save) {
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
        int returnVal;
        
        if (save) {
            returnVal = fileChooser.showSaveDialog(this);
        } else {
            returnVal = fileChooser.showOpenDialog(this);
        }
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        
        return null;
    }
    
    private void drawPanelMousePressed(MouseEvent evt) {
        Asteroid asteroid = asteroids.getSelected();
        
        if (asteroid == null && SwingUtilities.isLeftMouseButton(evt)) {
            asteroid = getSelectedAsteroid();
        }
        
        if (asteroid == null) {
            return;
        }
        
        Point point = evt.getPoint();
        
        if (SwingUtilities.isLeftMouseButton(evt)) {
            movingPointIndex = asteroid.getPointAt(point);
            
            if (movingPointIndex == -1) {
                int sideIndex = asteroid.getSideAt(point);
                
                if (sideIndex >= 0) {
                    movingPointIndex = sideIndex + 1;
                    asteroid.insertPoint(movingPointIndex, point.x, point.y);
                } else {
                    asteroid.addPoint(point.x, point.y);
                    movingPointIndex = asteroid.getPointCount() - 1;
                }
            }
            
            drawPanel.repaint();
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            int pointIndex = asteroid.getPointAt(point);
            
            if (pointIndex >= 0) {
                asteroid.removePoint(pointIndex);
                drawPanel.repaint();
            }
            movingPointIndex = -1;
        }
    }
    
    private void drawPanelMouseDragged(MouseEvent evt) {
        Asteroid asteroid = asteroids.getSelected();
        
        if (asteroid != null && movingPointIndex >= 0) {
            asteroid.movePoint(movingPointIndex, evt.getX(), evt.getY());
            drawPanel.repaint();
        }
    }
    
    private void drawPanelMouseWheelMoved(MouseWheelEvent evt) {
        Asteroid asteroid = asteroids.getSelected();
        
        if (asteroid == null) {
            return;
        }
        
        if (evt.isControlDown()) {
            double factor = Math.pow(1.1, -evt.getPreciseWheelRotation());
            asteroid.scale(factor);
        } else {
            asteroid.rotate(-evt.getPreciseWheelRotation() * 0.15);
        }
        
        drawPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drawPanel = new DrawPanel();
        newAsteroidButton = new javax.swing.JButton();
        centerAsteroidButton = new javax.swing.JButton();
        clearAsteroidButton = new javax.swing.JButton();
        saveAsteroidButton = new javax.swing.JButton();
        loadAsteroidButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        newAsteroidButton.setText("New Asteroid");
        newAsteroidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAsteroidButtonActionPerformed(evt);
            }
        });

        centerAsteroidButton.setText("Center Asteroid");

        clearAsteroidButton.setText("Clear Asteroid");

        saveAsteroidButton.setText("Save Asteroid");

        loadAsteroidButton.setText("Load Asteroid");

        jScrollPane1.setViewportView(itemList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(drawPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saveAsteroidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadAsteroidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearAsteroidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(centerAsteroidButton)
                            .addComponent(newAsteroidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(newAsteroidButton)
                .addGap(18, 18, 18)
                .addComponent(centerAsteroidButton)
                .addGap(47, 47, 47)
                .addComponent(clearAsteroidButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveAsteroidButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadAsteroidButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newAsteroidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAsteroidButtonActionPerformed
        asteroids.addNew();
        updateAsteroidList();
        drawPanel.repaint();
    }//GEN-LAST:event_newAsteroidButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton centerAsteroidButton;
    private javax.swing.JButton clearAsteroidButton;
    private DrawPanel drawPanel;
    private javax.swing.JList<String> itemList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadAsteroidButton;
    private javax.swing.JButton newAsteroidButton;
    private javax.swing.JButton saveAsteroidButton;
    // End of variables declaration//GEN-END:variables
}
