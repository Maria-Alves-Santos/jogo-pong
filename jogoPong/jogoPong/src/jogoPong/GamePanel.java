package jogoPong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Ball ball;
    private Paddle player1, player2;
    private Score score;
    private Thread gameThread;
    private boolean running = false;

    public GamePanel() {
        ball = new Ball(390, 290, 20, 20);
        player1 = new Paddle(20, 250, 10, 100);  // Esquerda
        player2 = new Paddle(770, 250, 10, 100); // Direita
        score = new Score();

        setFocusable(true);
        addKeyListener(this);
    }

    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            move();
            checkCollisions();
            repaint();

            try {
                Thread.sleep(10); // Controla a velocidade do jogo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void move() {
        ball.move();
        player1.move(getHeight());
        player2.move(getHeight());
    }

    private void checkCollisions() {
        // Colisão com bordas superior e inferior
        if (ball.y <= 0 || ball.y >= getHeight() - ball.height) ball.reverseY();

        // Colisão com o jogador 1
        if (ball.getBounds().intersects(player1.getBounds())) ball.reverseX();

        // Colisão com o jogador 2
        if (ball.getBounds().intersects(player2.getBounds())) ball.reverseX();

        // Pontuação e vidas
        if (ball.x <= 0) {
            score.addPoint(2); // Jogador 2 ganha ponto
            score.loseLife(1); // Jogador 1 perde vida
            ball.reset(390, 290);
        } else if (ball.x >= getWidth()) {
            score.addPoint(1); // Jogador 1 ganha ponto
            score.loseLife(2); // Jogador 2 perde vida
            ball.reset(390, 290);
        }

        // Verifica as condições de vitória
        if (score.getScore(1) >= 20 || score.getLives(2) == 0) {
            running = false;
            JOptionPane.showMessageDialog(this, "Jogador 1 venceu!");
            System.exit(0);
        } else if (score.getScore(2) >= 20 || score.getLives(1) == 0) {
            running = false;
            JOptionPane.showMessageDialog(this, "Jogador 2 venceu!");
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        ball.draw(g);
        player1.draw(g);
        player2.draw(g);
        score.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) player1.setDirection(-1);
        if (e.getKeyCode() == KeyEvent.VK_S) player1.setDirection(1);
        if (e.getKeyCode() == KeyEvent.VK_UP) player2.setDirection(-1);
        if (e.getKeyCode() == KeyEvent.VK_DOWN) player2.setDirection(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) player1.setDirection(0);
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) player2.setDirection(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}