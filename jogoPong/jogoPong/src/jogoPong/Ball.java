package jogoPong;

import java.awt.*;

public class Ball {
    int x, y, width, height;
    int xSpeed = 3, ySpeed = 2;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
    }

    public void reverseX() {
        xSpeed = -xSpeed;
    }

    public void reverseY() {
        ySpeed = -ySpeed;
    }

    public void reset(int startX, int startY) {
        x = startX;
        y = startY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}