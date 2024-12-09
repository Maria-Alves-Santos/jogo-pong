package jogoPong;

import java.awt.*;

public class Score {
    private int score1 = 0, score2 = 0;
    private int lives1 = 5, lives2 = 5;

    public void addPoint(int player) {
        if (player == 1) score1++;
        else if (player == 2) score2++;
    }

    public void loseLife(int player) {
        if (player == 1) lives1--;
        else if (player == 2) lives2--;
    }

    public int getScore(int player) {
        return player == 1 ? score1 : score2;
    }

    public int getLives(int player) {
        return player == 1 ? lives1 : lives2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Jogador 1 - Pontos: " + score1 + " | Vidas: " + lives1, 10, 20);
        g.drawString("Jogador 2 - Pontos: " + score2 + " | Vidas: " + lives2, 400, 20);
    }
}