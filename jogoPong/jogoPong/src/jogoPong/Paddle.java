package jogoPong;

import java.awt.*;

public class Paddle {
    int x, y, width, height;
    int ySpeed = 0;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setDirection(int direction) {
        ySpeed = direction * 5;
    }

    public void move(int screenHeight) {
        y += ySpeed;
        if (y < 0) y = 0;
        if (y > screenHeight - height) y = screenHeight - height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
