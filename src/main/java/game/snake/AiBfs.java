package game.snake;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class AiBfs {
	// ��ͼ�ṹ
	private byte[][] struct;
	// ��Ҫ������Ŀ������
	private int tX, tY;
	// ������ʹ��Ľڵ����
	private ArrayDeque<int[]> pointers;
	
	/** ���캯�� */
	public AiBfs(int r, int c, int tX, int tY){
		struct = new byte[r][c];
		pointers = new ArrayDeque<>(r * c);
		struct[tX][tY] = 2;	// ���ˮ��
		this.tX = tX;
		this.tY = tY;
	}
	
	// ʹ��ָ�����������δ���ʹ����ٽ�����
	private int[][] seek(int x, int y){
		int[] u = new int[]{x, y - 1};		// ��
		int[] d = new int[]{x, y + 1};	// ��
		int[] l = new int[]{x - 1, y};		// ��
		int[] r = new int[]{x + 1, y};	// ��
		return new int[][]{u, d, l, r};
	}

	/** ˼����һ����ô�� */
	public void think(Snake snake){
		Block header = snake.getHeader();
		int[][] udlr = seek(header.getColIndex(), header.getRowIndex());
	}
	
	public static void main(String[] args) {
		AiBfs ab = new AiBfs(6, 10, 3, 5);
		for (int i = 0; i < ab.struct.length; i++) {
			for (int k = 0; k < ab.struct[i].length; k++) {
				System.out.print(ab.struct[i][k]);
				System.out.print(",");
			}
			System.out.println();
		}
		
	}
}
