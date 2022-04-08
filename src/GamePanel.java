import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREE_WIDTH = 600;
    static final int SCREE_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNTIS = (SCREE_WIDTH*SCREE_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNTIS];
    final int y[] = new int[GAME_UNTIS];
    int bodyParts = 6;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREE_WIDTH, SCREE_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    };

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    };

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawer(g);
    };

    public void drawer(Graphics g) {

    };

    public void newApple() {

    }

    public void move() {

    };

    public void checkApple() {

    };

    public void checkCollisions() {

    };

    public void gameOver(Graphics g) {

    };

    @Override
    public void actionPerformed(ActionEvent e) {

    };

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
