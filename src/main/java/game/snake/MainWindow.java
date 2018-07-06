package game.snake;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/** ������ */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 5077447958225132515L;

	public static void main(String[] args) {
		// ��������
		Font font = new Font("΢���ź�", Font.BOLD, 16);
		FontUIResource fur = new FontUIResource(font);
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key = keys.nextElement();
			Object val = UIManager.get(key);
			if(val instanceof FontUIResource){
				UIManager.put(key, fur);
			}			
		}
		
		// ʵ����һ������
		MainWindow win = new MainWindow();
		// ���ñ���
		win.setTitle("����һ��java����");
		// ���ô��ڴ�С
		win.setSize(480,340);
		// �رմ���ʱ����ֹ����
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ��Ϸ
		Game game = new Game();
		
		// ���
		Panel panel = new Panel(game);
		win.add(panel);
		// ������ʾ
		panel.setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));
		// �����С
		win.pack();
		
		// ȡ����Ļ��С;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int width = (int)(screenSize.getWidth()-win.getWidth())/2;
		int height = (int)(screenSize.getHeight()-win.getHeight())/2;
		// ���ô���λ�� �� ��Ļ������
		win.setLocation(width,height);
		// ���ô��ڿɼ�
		win.setVisible(true);

		
		// ������Ӽ������¼�
		win.addKeyListener(game.createKeyListener());
		// �������ˢ��
		panel.startUpdate();
		
		// ������Ϸˢ��
		game.start();
		
		
	}
	
}
