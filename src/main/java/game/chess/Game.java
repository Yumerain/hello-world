package game.chess;

import java.util.Scanner;

public class Game {
	
	public static void main(String[] args) {
		Game lc = new Game();
		lc.start();
	}
	
	// trueΪAͶ���ӣ�falseΪBͶ����
	private boolean flag = true;
	// ��Ϸ���A
	private Player a = new Player("[�Ƿ�]");
	// ��Ϸ��B
	private Player b = new Player("[����]");

	public void start() {
		// ���빤��
		Scanner sc = new Scanner(System.in);
		
		// ��Ϸ������ʾ��
		System.out.println("��������������������������������������");
		System.out.println("�������������������������������������");
		System.out.println("��������������������������������������");
		System.out.println("*1.�������Ͷ������");
		System.out.println("*2�������ӵ�������Ӧ����");
		System.out.println("*3�ȵ����յ��ʤ��");
		
		
		// ��Ϸ���̿�ʼ
		Chessboard cb = new Chessboard(); // ����
		// ��Ϸ��ͼ
		String[] map = new String[]{
				"��","��","��","��","��",    "��","��","��","��","��",
				"10","��","��","��","��",    "15","��","��","��","��",
				"20","��","��","��","��",    "25","��","��","��","��",
				"30","��","��","��","��",    "35","��","��","��","��",
				"40","��","��","��","��",    "45","��","��","��","��",
				"50","��","��","��","��",    "55","��","��","��","��",
				"60","��","��","��","64",    "��"
		};
		
		int end = 66;  // �յ�λ��
		
		// ��ʾ���̳�ʼ״̬
		cb.printChessboard(map, a.position, b.position);
		
		while(a.position<end-1 && b.position<end-1){ // ֻҪ���κ�һ����ҵ����յ�����Ϸ����	
			System.out.println("\n\n\n\n\n");
			int step = random();
			
			if(flag){
				System.out.print("�����"+a.name+"Ͷ���ӣ������������ַ�����");
				sc.next();
				// ���A�ƶ�
				a.move(step, map);				
				
				// �����Ƿ��� ����Ͷ���¼��������Ƿ�ı��ʶ
				if(a.event != 3){
					flag = false;					
				}				
				
				// �ƶ���������ʾ����
				cb.printChessboard(map, a.position, b.position);				
				// �ƶ���������ʾλ��
				a.show(b);
			}else{
				System.out.print("�����"+b.name+"Ͷ���ӣ������������ַ�����");
				sc.next();
				// ���B�ƶ�
				b.move(step, map);
				
				// �����Ƿ��� ����Ͷ���¼��������Ƿ�ı��ʶ
				if(b.event != 3){
					flag = true;				
				}				
				
				// �ƶ���������ʾ����
				cb.printChessboard(map, a.position, b.position);				
				// �ƶ���������ʾλ��
				b.show(a);
			}
		}
		
		// ��Ϸ������ʾʤ����
		if(a.position>b.position){
			System.out.println("��ϲ���"+a.name+"ʤ����");
		}else{
			System.out.println("��ϲ���"+b.name+"ʤ����");
		}
	}
	
	/** ����һ��1~6������ */
	public static int random(){		
		int num = 0;
		while(num<1 || num>6){
			double rd = Math.random();
			num = (int)(rd*10);
		}
		return num;		
	}
	
}
