package game.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener{

	// ̰ʳ�ߵ�����
	private Snake snake;
	
	// ��Ϸ������
	private Game game;
	
	public GameKeyListener(Snake snake, Game game){
		this.snake = snake;
		this.game = game;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37: // ��
			snake.turnLeft();
			break;
		case 40: // ��
			snake.turnDown();
			break;
		case 39: // ��
			snake.turnRight();
			break;
		case 38: // ��
			snake.turnUp();
			break;
		case 10: // ����/��ͣ
			game.toggleActive();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
