package game.snake;

import java.awt.Color;

/** ̰ʳ������ĵ�Ԫ��С�ڡ�С�� */
public class Block {
	
	public Block(int u, Color color, int r, int c){
		this.unit = u;
		this.color = color;
		this.rowIndex = r;
		this.colIndex = c;
	}
	// ��Ԫ��С
	private int unit;
	// ��Ԫ��ɫ
	private Color color;
	// ��Ԫ�����ڵ���
	private int rowIndex;
	// ��Ԫ�����ڵ���
	private int colIndex;
	
	public void setUnit(int unit){
		this.unit = unit;
	}
	public int getUnit(){
		return this.unit;
	}
	
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
