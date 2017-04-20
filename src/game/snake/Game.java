package game.snake;

import java.awt.Color;
import java.awt.Graphics;

public class Game extends Thread{
	
	private Color[] colors = new Color[]{
		Color.red,Color.green,Color.blue,
		Color.yellow,Color.orange,Color.cyan
	};
	
	// ��Ϸ���ӵ�����
	private int rows = 20;
	// ��Ϸ���ӵ�����
	private int cols = 32;
	// ��Ϸ��߷�
	private int total;
	// ͨ�ط���
	private int clearScore;
	
	// ������
	private int width;
	// ����߶�
	private int height;
	
	// ̰ʳ��
	private Snake snake;
	
	// ˮ��
	private Block fruit;
	
	// ��ʾ��Ϸ�Ƿ����
	private boolean gameOver = false;
	// ��ʾ��Ϸ�Ƿ�ͨ��
	private boolean clear = false;
	// ��ʾ��Ϸ�ǽ���/��ͣ
	private boolean active = false;
	
	//	 ����
	private int score = 0;
	
	// ��Ԫ��С
	public static final int UNIT = 30;
	
	public Game(){
		this.width = cols * UNIT;
		this.height = rows * UNIT;
		snake = new Snake(rows,cols);
		init();
	}

	private void init() {
		score = 0;
		total = (rows*cols-4)*100;
		clearScore = total;
		if(clearScore > total) clearScore = total;
		snake.init();
		// ����һ��λ�������ˮ��
		fruit = createFruit();
	}
	
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	

	// �������
	public void draw(Graphics g){
		// ���ԭ�л���
		g.clearRect(0, 0, width, height);
		
		// ����ˮ��
		g.setColor(fruit.getColor());
		g.fillRect(fruit.getColIndex()*UNIT, fruit.getRowIndex()*UNIT, fruit.getUnit(), fruit.getUnit());
		
		// ������֣��밴�س�����ʼ��Ϸ
		
		//g.drawString("�밴�س�����ʼ��Ϸ", 60, 100);
		
		// �����ߵ�����
		snake.draw(g);
		
		// ������Ϸ����
		g.setColor(Color.blue);
		g.drawString("������"+score, 10, 30);
		
		// �������֣�Game Over
		g.setColor(Color.red);
		if(gameOver){
			g.drawString("Game Over�����س������¿�ʼ", 300, 200);			
		}
		// �������֣���ϲ��ͨ�أ�
		g.setColor(Color.red);
		if(clear){
			g.drawString("��ϲ��ͨ�أ�", 300, 200);			
		}
		if(gameOver==false && active==false){
			g.drawString("���س�����ʼ", 300, 200);
		}
	}
	
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		while(true){
			if(active == true && gameOver == false){
				//int result = snake.move(fruit);	// ��ͨ�ƶ�
				int result = snake.autoMove(fruit);		// �����ƶ�
				if(result == 1){
					score += 100;
					if(score >= clearScore){
						clear = true;
						break;
					}
					fruit = createFruit();
				}else if(result == -1){
					gameOver = true;
				}
			}
			try {
				Thread.sleep(30);
			} catch (Exception e) {
			}
		}
	}
	
	// ����һ��λ�������ˮ��
	public Block createFruit(){
		int r = rows;
		int c = cols;
		// ��֤��λ������λ������Ϸ������֮�У����Ҳ����ߵ������ظ�
		while(r >= rows || c >= cols || snake.check(r, c)){
			r = (int)(Math.random()*10*rows);
			c = (int)(Math.random()*10*cols);
		}
		int rd = 6;
		while(rd >5){
			rd = (int)(Math.random()*10);
		}
		Block block = new Block(UNIT, colors[rd], r, c);		
		return block;
	}
	
	// �����������¼�ʵ��
	public GameKeyListener createKeyListener(){
		return new GameKeyListener(snake, this);
	}

	public void toggleActive() {
		if(gameOver==true){
			init();
			gameOver = false;
			active = true;
		}else{
			active = !active;
		}
	}
}
