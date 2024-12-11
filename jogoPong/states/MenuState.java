package jogoPong.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import jogoPong.Game;

public class MenuState implements State {
	private String[] options = {"START","HELP", "EXIT"}; // Opções do menu
	private Font font1; // Fonte para o título
	private Font font2;  // Fonte para as opções
	private int choice = 0;  // Indica a opção atualmente selecionada
	private int x = 0, y = 0, movex = 2, movey = 2; // Para movimentar um elemento visual (ex: bola)

	@Override
	public void init() {
		font1 = new Font("Dialog",Font.PLAIN,48); // Define a fonte do título
		font2 = new Font("Dialog",Font.PLAIN,24); // Define a fonte das opções

	}

	@Override
	public void update() {
		x += movex; // Atualiza a posição no eixo X
		y += movey; // Atualiza a posição no eixo Y
		
		limits(); // Verifica os limites para alterar a direção do movimento

	}

	private void limits() {
		if (x+15 > Game.width)  // Inverte a direção no eixo X
			movex = -1;	
		
		if (y+15 > Game.height) // Inverte a direção no eixo Y
			movey = -1;
		
		if(x < 0) 
			movex = 1; // Garante que não ultrapasse os limites
		if (y <0)
			movey = 1; // Garante que não ultrapasse os limites
				
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.width, Game.height); // Preenche o fundo de preto
		
		g.setColor(Color.WHITE); 
		g.setFont(font1);
		g.drawString("PONG",
				Game.width/2 - g.getFontMetrics().stringWidth("PONG")/2,
				Game.height * 1/4);
		
		g.setFont(font2);
		for (int i = 0; i < options.length; i++) {
			g.setColor(Color.WHITE);
			if (i == choice)
				g.setColor(Color.GREEN);
			g.drawString(options[i], Game.width/2 - g.getFontMetrics().stringWidth(options[i])/2
					, Game.height * 3/4 + g.getFontMetrics(font2).getHeight()* i);
			
		}
		
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, 15, 15); // Desenha o elemento visual ex: bola

	}

	@Override
	public void KeyPressed(int cod) {
		

	}

	@Override
	public void KeyReleased(int cod) {
		if (cod == KeyEvent.VK_W) { // Move para cima no menu
			choice --;
			if (choice < 0)
				choice = options.length - 1;
		} 
		if (cod == KeyEvent.VK_S) { // Move para baixo no menu
			choice ++;
			if (choice > options.length - 1)
				choice = 0;
		}
		if (cod == KeyEvent.VK_ENTER) { // Seleciona a opção atual
			select();
		}

	}

	private void select() {
		switch (choice) {
		case 0:
			StateManager.setState(StateManager.LEVEL1);
			break;  // Inicia o jogo
		case 1:
			break;  // Ajuda (não implementado)
		case 2:
			System.exit(0);  
			break;  // Sai do jogo
			
		default:
			break;
		}
		
	}

}
