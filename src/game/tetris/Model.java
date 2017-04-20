package game.tetris;

import java.awt.Graphics;

public interface Model {
	
	/** ��ʱ�뷽����ת */
	void anticlockwiseRotating();
	
	/** ˳ʱ�뷽����ת */
	void clockwiseRotating();
	
	/** �������µ��� */
	void dropDown();

	/** �����ƶ�һ�� */
	void moveDown();
	
	/** �����ƶ�һ�� */
	void moveLeft();

	/** �����ƶ�һ�� */
	void moveRight();
	
	/** ��ģ������ */
	void draw(Graphics g);

}
