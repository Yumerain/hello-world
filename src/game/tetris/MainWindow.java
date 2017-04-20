package game.tetris;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/** ������ */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 5077447958225132515L;

	public static void main(String[] args) {
		// ʵ����һ������
		MainWindow win = new MainWindow();
		// ���ñ���
		win.setTitle("����һ��java����");
		// �رմ���ʱ����ֹ����
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��Ϸ
		Game game = new Game();
		// ���
		Panel panel = new Panel(game);
		win.add(panel);
		panel.setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));
		// �����С
		win.pack();
		// ȡ����Ļ��С
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int width = (int)(screenSize.getWidth()-win.getWidth())/2;
		int height = (int)(screenSize.getHeight()-win.getHeight())/2;
		// ���ô���λ�� �� ��Ļ������
		win.setLocation(width,height);
		// ������Ӽ������¼�
		win.addKeyListener(game.createKeyListener());
		// �������ˢ��
		panel.startUpdate();
		// ������Ϸˢ��
		game.start();
		// ���ô��ڿɼ�
		win.setVisible(true);
	}
	
}
