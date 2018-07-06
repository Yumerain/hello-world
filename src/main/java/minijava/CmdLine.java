package minijava;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * �����н����û�����
 * @author zhangyu
 */
public class CmdLine {
	
	public static void main(String[] args) {
		new CmdLine().start();
	}

	private boolean multiLine = false;
	private StringBuilder buff = new StringBuilder();
	private BufferedReader reader;
	private MiniEnv env = new MiniEnv();

	public CmdLine(){
		try {
			reader = new BufferedReader(new InputStreamReader(System.in, System.getProperty("file.encoding")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void start(){
		try {
			String line = null;
			do {
				// ����ģʽ������ģʽ��ʾ��ͬ
				if(multiLine)
					System.out.print(">>"); 
				else
					System.out.print(">");
				
				// ��������
				line = reader.readLine();
				
				// ����ģʽ���˳���ʶ��
				if (!multiLine && "exit".equals(line)) {
					break;
				}
				
				// �л�������ģʽ��
				if(">>".equals(line)){
					multiLine = true;
					continue;
				}
				
				// �л�������ģʽ��
				if(">".equals(line)){
					multiLine = false;
					continue;
				}
				
				if(multiLine){
					if("<<".equals(line)){
						env.resolve(buff.toString());	// ����ģʽ�ⷢ�����
						buff.setLength(0);
					}else{
						buff.append(line);
					}					
				}else{
					env.resolve(line);							// ����ģʽ��������
				}
			} while (line != null);
			System.out.println("== Bye ==");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
