package game.tetris;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.loon.framework.javase.game.core.graphics.LColor;

public class Panel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = -3509619511193661382L;

	/** FPS��ʱ�1��(1000����) */
	private final int MAX_FPS_INTERVAL = 1000;
	/** ���FPS�� */
	private final int MAX_FPS = 500;
	/** ��ʾFPS������ */
	private final Font fpsFont = new Font("Dialog", Font.LAYOUT_LEFT_TO_RIGHT, 20);
	/** �Ƿ���ʾFPS */
	private boolean showFPS = true;
	/** ����FPS�ı�������ǰFPS��ʱ��������ʱ��ʼʱ�䡢ÿ�λ�������ʱ�䡢֡���� */
	private int currFPS, fpstIntervalTime, startTime, offsetTime, frameCount;
	
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
			g.setColor(LColor.white);
			g.drawString("FPS:" + currFPS, 10, 25);
		}
	}
	
	/** ����FPS */
	private void tickFrames(){
		frameCount++;
		fpstIntervalTime += offsetTime;
		if (fpstIntervalTime >= MAX_FPS_INTERVAL) {
			int timeNow = (int)System.currentTimeMillis();
			int elapsedTime = timeNow - startTime;
			currFPS = (frameCount / elapsedTime) * MAX_FPS_INTERVAL;	// 1���ڻ��ƵĴ���
			frameCount = 0;
			fpstIntervalTime = 0;
			startTime = timeNow;
		}
	}

	/** ���ˢ���߳� */
	public void run(){
		startTime = (int)System.currentTimeMillis();								// FPS��ʱ��ʼ
		offsetTime = (int)(1.0 / MAX_FPS * MAX_FPS_INTERVAL);		// ��FPS1�������֡��ʱ��ķ���ͳ��FPS
		while(true){
			// ˢ�´���
			this.repaint();
			Thread.yield();	
		}
	}	
	
}
