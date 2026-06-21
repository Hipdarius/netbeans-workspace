package u1_mocktestjune;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {

    private CryptoBox cryptoBox = new CryptoBox();
    private Game game = null;

    private JTextArea clearTextField;
    private JTextArea cipherTextField;
    private DrawPanel drawPanel;
    private JLabel statusLabel;

    private JButton encryptButton;
    private JButton decryptButton;
    private JButton loadCryptoButton;
    private JButton saveCryptoButton;
    private JButton addBallButton;
    private JButton clearBallsButton;
    private JButton loadBallsButton;
    private JButton saveBallsButton;

    public MainFrame() {
        initComponents();
        game = new Game(drawPanel.getPreferredSize().width, drawPanel.getPreferredSize().height);
        drawPanel.setGame(game);
        setTitle("H1/H2 Exam Trainer");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        clearTextField = new JTextArea(5, 28);
        cipherTextField = new JTextArea(5, 28);
        drawPanel = new DrawPanel();
        statusLabel = new JLabel("Mock test skeleton: complete the requested methods.");

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        loadCryptoButton = new JButton("Load .cry");
        saveCryptoButton = new JButton("Save .cry");
        addBallButton = new JButton("Add Ball");
        clearBallsButton = new JButton("Clear Balls");
        loadBallsButton = new JButton("Load .abs");
        saveBallsButton = new JButton("Save .abs");

        JPanel cryptoPanel = createCryptoPanel();
        JPanel ballsPanel = createBallsPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, cryptoPanel, ballsPanel);
        splitPane.setResizeWeight(0.45);

        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });

        loadCryptoButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loadCryptoButtonActionPerformed(evt);
            }
        });

        saveCryptoButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveCryptoButtonActionPerformed(evt);
            }
        });

        addBallButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addBallButtonActionPerformed(evt);
            }
        });

        clearBallsButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                clearBallsButtonActionPerformed(evt);
            }
        });

        loadBallsButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loadBallsButtonActionPerformed(evt);
            }
        });

        saveBallsButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveBallsButtonActionPerformed(evt);
            }
        });
    }

    private JPanel createCryptoPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel textPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        textPanel.add(new JScrollPane(clearTextField));
        textPanel.add(new JScrollPane(cipherTextField));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        buttonPanel.add(loadCryptoButton);
        buttonPanel.add(saveCryptoButton);

        panel.add(new JLabel("H1 CryptoBox"), BorderLayout.NORTH);
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createBallsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addBallButton);
        buttonPanel.add(clearBallsButton);
        buttonPanel.add(loadBallsButton);
        buttonPanel.add(saveBallsButton);

        panel.add(new JLabel("H2 Angry Balls file I/O"), BorderLayout.NORTH);
        panel.add(drawPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Read clear text, encode it, and display the cipher text.
    }

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Read cipher text, decode it, and display the clear text.
    }

    private void loadCryptoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Choose a .cry file and load its first line into cipherTextField.
    }

    private void saveCryptoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Choose a .cry file and save the cipherTextField content.
    }

    private void addBallButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Add a red ball to the game and repaint the panel.
    }

    private void clearBallsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Clear the red balls and repaint the panel.
    }

    private void loadBallsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Choose a .abs file, load the balls, and repaint the panel.
    }

    private void saveBallsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Choose a .abs file and save the current red balls.
    }
}
