package game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	public static final int START_ROW = 1;
	public static final int START_COL = 16;
	
	private AI ai = new AI();
	
	// ��Ϸ���ӵ�����
	private int rows;
	// ��Ϸ���ӵ�����
	private int cols;

	// �ߵ�����
	public List<Block> body = new ArrayList<Block>();
	
	// ��ǰ���ķ��򣺡� �� �� �� 
	public char direction = '��';
	// ��һ��Ҫǰ���ķ���
	public char nextDirection = '��';
	
	// ��ͷ����λ��
	public int row;
	
	// ��ͷ����λ��
	public int col;
	
	public Snake(int rs, int cs){
		// ��Ϸ���Ӵ�С
		this.rows = rs;
		this.cols = cs;
		init();
	}
	
	public void init() {
		// ��ǰ���ķ��򣺡� �� �� �� 
		this.direction = '��';
		this.nextDirection = '��';
		// ��ͷ��ʼλ��
		this.row = START_ROW;
		this.col = START_COL;	
		// ʵ�������ߵ�4����ʼ�ؽ�
		Block header = new Block(Game.UNIT, Color.black, START_ROW, START_COL);
		Block b1 = new Block(Game.UNIT, Color.gray, START_ROW, START_COL+1);
		Block b2 = new Block(Game.UNIT, Color.gray, START_ROW, START_COL+2);
		Block b3 = new Block(Game.UNIT, Color.gray, START_ROW, START_COL+3);
		body.clear();
		body.add(header);
		body.add(b1);
		body.add(b2);
		body.add(b3);
	}
	
	// �����ߵ�����
	public void draw(Graphics g){
		for(int i=0; i<body.size(); i++){
			Block block = body.get(i);
			g.setColor(block.getColor());
			g.fillRect(block.getColIndex()*Game.UNIT, block.getRowIndex()*Game.UNIT, block.getUnit(),  block.getUnit());
		}
	}
	
	/**
	 * AI�������ƶ�
	 */
	public int autoMove(Block fruit){
		// �����ж�һ���·���
		ai.think(this, fruit, rows, cols);
		return move(fruit);
	}
	
	/**
	 * ̰ʳ�ߵ��ƶ�����
	 * @param fruit
	 * @return 1-�Ե�ˮ����2-��ͨ�ƶ�
	 */
	public int move(Block fruit){
		// �����ߵ�ǰ�������жϳ���ͷ����һ����λ��
		switch (nextDirection) {
		case '��':
			this.col--;
			break;
		case '��':
			this.row--;
			break;
		case '��':
			this.col++;
			break;
		case '��':
			this.row++;
			break;
		}
		direction = nextDirection;
		// �����߱���ı仯
		// ���µ���ͷ
		Block newHeader = new Block(Game.UNIT, Color.black, row, col);
		// �Ѿɵ���ͷ����ɫ�ĳ��������ɫ
		body.get(0).setColor(Color.gray);		
		// �߳���һ�ڣ�����ͷǰ���������ߵ�ǰ���������һ���µ���ͷ
		body.add(0, newHeader);
		
		// ������µ���ͷλ����ˮ����ͬ�����߳Ե���ˮ��������һ��
		if(col == fruit.getColIndex() && row == fruit.getRowIndex()){
			// �Ե�ˮ����ֱ�ӷ��أ������Ƴ���β
			return 1;
		}		
		// ������һ����ͷ���Ƴ���β����ʾ��ǰ����һ��
		body.remove(body.size() - 1);
		
		// ����ǰ�����ж����Ƿ�ײǽ
		// �ж����Ƿ�ײǽ��ײ��������
		if(col>=cols || row>=rows || col<0 || row <0) return -1;
		// �ж����Ƿ�ײ���Լ������壬ײ��������
		for (int i = 1; i < body.size(); i++) {
			Block item = body.get(i);
			// �����������ÿһ������ͷ��λ����Ƚϣ����λ���ظ�����ײ���Լ�������
			if(col==item.getColIndex() && row==item.getRowIndex()){
				return -1;
			}
		}
		
		// ��ûײ������ͨ�ƶ�
		return 0;		
	}
	
	/**
	 * ���ˮ��λ���Ƿ����Լ��������ظ�
	 * @param r
	 * @param c
	 * @return �ظ�����true, ���򷵻�false
	 */
	public boolean check(int r, int c){
		for (int i = 0; i < body.size(); i++) {
			Block item = body.get(i);
			// �����������ÿһ������ͷ��λ����Ƚϣ����λ���ظ�����ײ���Լ�������
			//System.out.println("r=" + r + ",sr=" +item.getColIndex() + ",c=" + c + ",sc=" + item.getRowIndex());
			if(c==item.getColIndex() && r==item.getRowIndex()){
				return true;
			}
		}
		return false;
	}
	
	// ̰ʳ��ת��
	public void turnLeft(){if(direction!='��')nextDirection='��';}
	public void turnUp(){if(direction!='��')nextDirection='��';}
	public void turnRight(){if(direction!='��')nextDirection='��';}
	public void turnDown(){if(direction!='��')nextDirection='��';}
	
	// ����ԭ��·��
	public void keepDierection(){
		nextDirection = direction;
	}
	
	/**
	 * ���ָ������step���ڣ����ٲ���ײ�ϣ�-1��ʾ����ײ��
	 * @param step
	 * @return
	 */
	public int checkBoomStep(int step, char dir){
		int count = 0;
		boolean boom = false;
		for (int i = 1; i <= step; i++) {
			count++;
			switch (dir) {
			case '��':
				if(check(row - i, col)==true){
					boom = true;
				}
				break;
			case '��':
				if(check(row + i, col)==true){
					boom = true;
				}
				break;
			case '��':
				if(check(row, col - i)==true){
					boom = true;
				}
				break;
			case '��':
				if(check(row, col + i)==true){
					boom = true;
				}
				break;
			}
			if(boom){
				break;
			}
		}
		if(boom){
			return count;
		}else{
			return -1;
		}
	}

	public Block getHeader() {
		return body.get(0);
	}
}
