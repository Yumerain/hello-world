package game.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * �滭���
 * @author zhangyu
 */
public class Panel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = -3509619511193661382L;

	/** FPS��ʱ�0.5��(500���룬500*1000000����) */
	private final long MAX_FPS_INTERVAL = 500*1000000;
	/** ���FPS�� */
	private final int MAX_FPS = 250;
	/** ��ʾFPS������ */
	private final Font fpsFont = new Font("Dialog", Font.LAYOUT_LEFT_TO_RIGHT, 20);
	/** �Ƿ���ʾFPS */
	private boolean showFPS = true;
	/** ����FPS�ı�������ǰFPS��֡���� */
	private int currFPS, frameCount;
	/** ����FPS�ı�����ʱ��������ʱ��ʼʱ�䡢ÿ�λ�������ʱ�� */
	private long fpstIntervalTime, startTime, offsetTime;
	
	private Game game;
	
	public Panel(Game game){
		this.game = game;
	}
	
	// �������ˢ��
	public void startUpdate(){
		new Thread(this).start();
	}
	
	@Override
	public void paint(Graphics g) {
		// ������浽����
		game.draw(g);
		// ��ʾFPS
		if (showFPS) {
			tickFrames();
			g.setFont(fpsFont);
			g.setColor(Color.white);
			g.drawString("FPS:" + currFPS, 10, 25);
		}
	}
	
	/** ����FPS */
	private void tickFrames(){
		frameCount++;
		fpstIntervalTime += offsetTime;
		// ʱ��ﵽ����Ҫ�����ʱ��
		if (fpstIntervalTime >= MAX_FPS_INTERVAL) {
			long timeNow = System.nanoTime();
			long elapsedTime = timeNow - startTime;
			currFPS = (int)(frameCount * MAX_FPS_INTERVAL / elapsedTime);	// ʱ���ڻ��ƵĴ���
			frameCount = 0;
			fpstIntervalTime = 0;
			startTime = timeNow;
		}
	}

	/** ���ˢ���߳� */
	public void run(){
		startTime = System.nanoTime();								// FPS��ʱ��ʼ
		offsetTime = (long)(1.0 / MAX_FPS * MAX_FPS_INTERVAL);		// ��FPS1�������֡��ʱ��ķ���ͳ��FPS
		while(true){
			// ˢ�´���
			this.repaint();
			Thread.yield();	
		}
	}	
	
}
