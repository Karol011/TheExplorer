package entity;

import src.GamePanel;
import src.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        xPosition = 100;
        yPosition = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyHandler.upPressed) {
            direction = "up";
            yPosition -= speed;
        }
        if (keyHandler.downPressed) {
            direction = "down";
            yPosition += speed;
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            xPosition -= speed;
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            xPosition += speed;
        }
    }

    public void draw(Graphics2D g2) {
   /*     g2.setColor(Color.white);
        g2.fillRect(xPosition, yPosition, gamePanel.tileSize, gamePanel.tileSize);*/
        BufferedImage image = null;
        switch (direction) {
            case "up" -> image = up1;
            case "down" -> image = down1;
            case "left" -> image = left1;
            case "right" -> image = right2;
        }
        g2.drawImage(image, xPosition, yPosition, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("gesler.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("gesler.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("gesler.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("gesler.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

