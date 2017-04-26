package game.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * ��Ϸ�߼�����ʵ��
 * @author zhangyu
 */
public class Game extends Thread implements KeyListener{
	
	/** ��Ϸ��ʱ�ӿ̶ȵδ� */
	public final long TICK = 10;
	
	// ��Ϸ���ӵ�����
	private int rows = 26;
	// ��Ϸ���ӵ�����
	private int cols = 16;
	
	// ������
	private int width;
	// ����߶�
	private int height;
	
	// ״̬
	private Status status = Status.START;
	
	//	 ����
	private int score = 0;
	
	// �ȼ����ȼ�Խ�������ٶ�Խ��
	private int level = 1;
	
	// ��Ϸˢ�¼��
	private int interval = 0;
	
	// ��Ԫ��С
	public static final int UNIT = 30;
	// ������Ϣ�� ���
	public static final int SIDE = 100; 
	
	private ModelFactory modelFactory = ModelFactory.getInstance();
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
		model = modelFactory.buildRandom(rows, cols);
		nextModel = modelFactory.buildRandom(rows, cols);
	}
	
	
	// �������
	public void draw(Graphics g){
		// ���(���)ԭ�л���
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		// ��巽��
		g.setColor(Color.white);
		g.drawRect(0, 0, cols * UNIT, height);
		// ���������ģ��
		model.draw(g);
		// ��һ����Ҫ���ֵ�ģ��
		nextModel.draw(g);
		// ������Ϸ����
		g.drawString("������"+score, width - 80, 30);
		// ����
		if(status == Status.OVER){
			g.setColor(Color.red);
			g.drawString("Game Over", 300, 200);			
		}
	}
	
	// �߳�
	public void run(){
		try {
			while(true){
				update();
				Thread.sleep(TICK);
			}
		} catch (Exception e) {
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	//-----------------------------KeyListener��ʵ��-----------------------------//
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 38: // ��/w
		case 87:
			break;
		case 40: // ��/s
		case 83:
			model.moveDown();
			break;
		case 37: // ��/a
		case 65:
			model.moveLeft();
			break;
		case 39: // ��/d
		case 68:
			model.moveRight();
			break;
		case 74: 	// A(j)
			model.anticlockwiseRotating();
			break;
		case 75:   // B(k)
			model.clockwiseRotating();
			break;
		case 70:   // ����/ѡ��(f)
			break;
		case 72:   // ��ʼ/��ͣ(h)
			control();
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	//-----------------------------KeyListener��ʵ��-----------------------------//
	
	// ״̬����
	private void control() {
		if(status == Status.PAUSE)
			status = Status.PLAYING;
		else if(status == Status.PLAYING)
			status = Status.PAUSE;
		else if(status == Status.START)
			status = Status.PLAYING;
	}
	
	// ���ȸ���
	private void update() {
		interval++;
		// ��ǰģ���Զ��½�һ��
		if(interval >= level * TICK){
			model.moveDown();
			interval = 0;
		}
	}
	
	/** ״̬���� */
	enum Status{
		START,
		PAUSE,
		PLAYING,
		OVER
	}
}
