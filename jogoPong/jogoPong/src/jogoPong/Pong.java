package jogoPong;

import javax.swing.*;

public class Pong {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong - 2 Jogadores");
        GamePanel gamePanel = new GamePanel();
        
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);

        gamePanel.startGame();
    }
}