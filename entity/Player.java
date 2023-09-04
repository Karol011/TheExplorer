package entity;

import src.GamePanel;
import src.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    public static final int DELAY_FOR_SPRITE_ANIMATION = 12;
    public int screenX = 0;
    public int screenY = 0;
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        solidArea = new Rectangle(8, 16, 32, 32); //We dont want to make player hitboxes take all of the player texture

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 24;
        worldY = gamePanel.tileSize * 28;
        speed = 4;
        direction = Direction.DOWN;
    }

    public void update() {
        boolean directionKeyPressed = (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed);

        if (directionKeyPressed) {
            if (keyHandler.upPressed) {
                direction = Direction.UP;
            } else if (keyHandler.downPressed) {
                direction = Direction.DOWN;
            } else if (keyHandler.leftPressed) {
                direction = Direction.LEFT;
            } else if (keyHandler.rightPressed) {
                direction = Direction.RIGHT;
            }


            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            //CHECK TILE COLLISION
            if (collisionOn == false) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }
            }
            spriteCounter++;
            if (directionKeyPressed) {
                if (spriteCounter > DELAY_FOR_SPRITE_ANIMATION) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
   /*     g2.setColor(Color.white);
        g2.fillRect(xPosition, yPosition, gamePanel.tileSize, gamePanel.tileSize);*/
        BufferedImage image = null;
        switch (direction) {
            case UP -> {
                image = up1;
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case DOWN -> {
                image = down1;
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case RIGHT -> {
                image = left1;
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case LEFT -> {
                image = right1;
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler-up1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler-up2.png"));

            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler-down2.png"));

            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler-left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler-left2.png"));

            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler-right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/gesler-right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

