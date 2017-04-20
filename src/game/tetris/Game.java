package game.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Game extends Thread{
	
	// ��Ϸ���ӵ�����
	private int rows = 26;
	// ��Ϸ���ӵ�����
	private int cols = 16;
	
	// ������
	private int width;
	// ����߶�
	private int height;
	
	// ��ʾ��Ϸ�Ƿ����
	private boolean gameOver = false;
	
	//	 ����
	private int score = 0;
	
	// ��Ԫ��С
	public static final int UNIT = 30;
	// ������Ϣ�� ���
	public static final int SIDE = 100; 
	
	// �����½���ģ��
	private Model model;
	// ��һ����Ҫ���ֵ�ģ��
	private Model nextModel;
	// �Ѿ����±������˵�ǽ
	private List<Block> wall = new ArrayList<Block>(rows * cols);

	public Game(){
		// ���Ҫ��һ��������ʾ��Ϣ
		this.width = cols * UNIT + SIDE;
		this.height = rows * UNIT;
		model = createModel();
		nextModel = createModel();
	}
	
	
	// �������
	public void draw(Graphics g){
		// ���(���)ԭ�л���
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);		
		// ���������ģ��
		//model.draw(g);
		// ��һ����Ҫ���ֵ�ģ��
		//nextModel.draw(g);
		// ������Ϸ����
		g.setColor(Color.white);
		g.drawString("������"+score, width - 80, 30);
		// ����
		if(gameOver){
			g.setColor(Color.red);
			g.drawString("Game Over", 300, 200);			
		}
	}
	
	public void run(){
		try {
			while(true){
				Thread.sleep(20);
			}
		} catch (Exception e) {
		}
	}
	
	// �����������¼�ʵ��
	public GameKeyListener createKeyListener(){
		return new GameKeyListener();
	}
	
	// ����ģ��
	private Model createModel(){
		return  null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
