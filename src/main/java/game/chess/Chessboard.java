package game.chess;

public class Chessboard {
	
	/**
	 * @param map ��Ϸ��
	 * @param a ���λ��
	 * @param b ���λ��
	 */
	public void printChessboard(String[] map, int a, int b){
		
		// ����һ�ݵ�ͼ�ĸ���
		String[] mapCopy = new String[map.length];
		System.arraycopy(map, 0, mapCopy, 0, map.length);
		
		// �ѵ���������ڵ�λ������Ҵ����滻
		mapCopy[a] = "��";
		mapCopy[b] = "��";
		if(a == b) mapCopy[b] = "��";
		
		// �����1�У�[����������������������������������������]
		for (int i = 0; i < 20; i++) {
			System.out.print(mapCopy[i]);
		}
		System.out.println();
		
		// �����2��3��4��:[                   ��]
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 19; k++) {
				System.out.print("��");
			}
			System.out.println(mapCopy[20+i]);
		}
		
		// �����5�У�[����������������������������������������]
		for (int i = 19; i >=0; i--) {
			System.out.print(mapCopy[23+i]);	
		}
		System.out.println();
		
		// �����6��7��8�У�[��                   ]
		for (int i = 0; i < 3; i++) {
			System.out.print(mapCopy[43+i]);
			for (int k = 0; k < 19; k++) {
				System.out.print("��");
			}
			System.out.println();
		}
		
		// �����9�У�[����������������������������������������]
		for (int i = 0; i < 20; i++) {
			System.out.print(mapCopy[46+i]);
		}
		System.out.println();
	}

}
