package src;

import entity.Entity;
import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);  //improves rendering speed
        this.addKeyListener(keyHandler);
        this.setFocusable(true);  //game panel can be "focused" to receive input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawtime = System.nanoTime() + drawInterval;


        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawtime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawtime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}
