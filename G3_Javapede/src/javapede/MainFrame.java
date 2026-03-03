package javapede;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainFrame extends JFrame {
    private final DrawPanel drawPanel;
    private final JButton startButton;
    private final JButton leftButton;
    private final JButton rightButton;
    private final JLabel scoreLabel;
    private final JButton javapedeButton;
    private final JButton bulletButton;
    private final Timer javapedeTimer;
    private final Timer bulletTimer;

    private Game game;

    public MainFrame() {
        super("Javapede");

        drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(600, 640));

        startButton = new JButton("Start");
        leftButton = new JButton("Left");
        rightButton = new JButton("Right");
        scoreLabel = new JLabel("Score: 0");

        javapedeButton = new JButton();
        bulletButton = new JButton();
        javapedeButton.setVisible(false);
        bulletButton.setVisible(false);

        javapedeTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javapedeButton.doClick();
            }
        });
        bulletTimer = new Timer(70, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bulletButton.doClick();
            }
        });

        JPanel top = new JPanel();
        top.add(startButton);
        top.add(leftButton);
        top.add(rightButton);
        top.add(scoreLabel);
        top.add(javapedeButton);
        top.add(bulletButton);

        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        bindActions();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void bindActions() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game(drawPanel.getWidth(), drawPanel.getHeight());
                drawPanel.setGame(game);
                scoreLabel.setText("Score: 0");
                setTitle("Javapede");
                javapedeTimer.start();
                drawPanel.repaint();
            }
        });

        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (game == null) {
                    return;
                }
                game.shoot();
                bulletTimer.start();
                drawPanel.repaint();
            }
        });

        javapedeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game == null) {
                    return;
                }
                boolean ok = game.moveJavapede();
                drawPanel.repaint();
                if (!ok) {
                    javapedeTimer.stop();
                    bulletTimer.stop();
                    setTitle("YOU LOST");
                }
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game == null) {
                    return;
                }
                game.movePlayer(-1);
                drawPanel.repaint();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game == null) {
                    return;
                }
                game.movePlayer(1);
                drawPanel.repaint();
            }
        });

        bulletButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game == null) {
                    return;
                }
                int r = game.moveBullet();
                scoreLabel.setText("Score: " + game.getScore());
                drawPanel.repaint();
                if (r == 0) {
                    bulletTimer.stop();
                }
                if (r == 2) {
                    bulletTimer.stop();
                    javapedeTimer.stop();
                    setTitle("YOU WON");
                }
            }
        });
    }
}
