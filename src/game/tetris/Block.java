package game.tetris;

import java.awt.Color;

/** ��Ԫ��С�ڡ�С�� */
public class Block {
	
	public Block(Color color, int r, int c){
		this.color = color;
		this.rowIndex = r;
		this.colIndex = c;
	}
	// ��Ԫ��ɫ
	private Color color;
	// ��Ԫ�����ڵ���
	private int rowIndex;
	// ��Ԫ�����ڵ���
	private int colIndex;
	
	public void setColor(Color color){
		this.color = color;
	}
	public Color getColor(){
		return this.color;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getColIndex() {
		return colIndex;
	}
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
	
	

}
