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
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREE_WIDTH, SCREE_HEIGHT));
        this.setBackground(new Color(13, 17, 23));
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

        if (running) {
            /*
            for (int i=0; i < SCREE_HEIGHT/UNIT_SIZE; i++) {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREE_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, i*SCREE_WIDTH, i*UNIT_SIZE);
            }; */
            // cor da maça
            // g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
            g.setColor(Color.BLUE);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(new Color(111, 26, 189));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(92, 0, 173));
                   // g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(new Color(16, 0, 156));
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREE_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    };

    public void newApple() {
        appleX = random.nextInt((int)(SCREE_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREE_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    };

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    };

    public void checkCollisions() {
        // checks if head collides with body
        for (int i  = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // check if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        // check if head touches right border
        if (x[0] > SCREE_WIDTH) {
            running = false;
        }
        // check if head touches top border
        if (y[0] < 0) {
            running = false;
        }
        // check if head touches bottom
        if (y[0] > SCREE_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    };

    public void gameOver(Graphics g) {
        // Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREE_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        // Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREE_HEIGHT - metrics2.stringWidth("Game Over"))/2, SCREE_HEIGHT/2);
    };

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    };

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
