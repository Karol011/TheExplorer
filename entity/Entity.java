package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int xPosition, yPosition, speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; //use this to store images
    public Direction direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
