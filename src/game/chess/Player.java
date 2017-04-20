package game.chess;

public class Player {
	
	public Player(String name){
		// �����ȡ����
		this.name = name;
		// ��ҳ�ʼλ��Ϊ0
		this.position = 0;
	}
	
	// ��ҵ�λ��
	public int position;
	// ��ʾ����ƶ���λ��
	public int step;

	// ��ҵ�����
	public String name;
	
	// ��ʾ���������ʱ�䣺1-��ͨ�ƶ���2-�ȵ����ף�3-����һ��������
	public int event;
	
	// ����ƶ��ķ���
	public void move(int step, String[] map){
		// �ƶ����λ��
		int forward = position + step;
		if(forward >= map.length) forward = map.length-1;
		
		if("��".equals(map[forward])){
			position = forward - 3;
			if(position<0) position = 0; // �����˵����֮��
			event = 2;
		}else if("��".equals(map[forward])){
			position = forward;
			event = 3;			
		}else if("��".equals(map[forward])){
			position = forward + 5;
			if(position>=map.length) position = map.length - 1; // ���ܳ�����ͼ�յ�
			event = 4;
		}else{
			position = forward;
			event = 1;
		}
		// ��¼����ƶ��Ĳ���
		this.step = step;
	}
	
	public void show(Player other){
		switch (event) {
		case 1:
			System.out.print("���"+name+"ǰ����" + step + "������ǰλ��=" + position + ", ���"+other.name+"λ��=" + other.position);
			break;
		case 2:
			System.out.print("���"+name+"��ǰ����" + step + "�����ֲȵ����׺���3������ǰλ��=" + position + ", ���"+other.name+"λ��=" + other.position);
			break;
		case 3:
			System.out.print("���"+name+"ǰ����" + step + "��������������������һ�Σ���ǰλ��=" + position + ", ���"+other.name+"λ��=" + other.position);
			break;
		case 4:
			System.out.print("���"+name+"ǰ����" + step + "����һ����˳ǰ����������ǰλ��=" + position + ", ���"+other.name+"λ��=" + other.position);
			break;	
		}
	}
}
