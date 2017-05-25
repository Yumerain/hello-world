package myhttp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * ������.ת��ͨϵͳר��������HTTP������
 * @author zhangyu
 */
public class QszHttpServer extends Thread {

	// ��־����
	private static Logger logger = Logger.getLogger(QszHttpServer.class);

	/**
	 * �������ض˿�����
	 * @param port
	 */
	public static void listen(int port){
		try {
			// ����
			QszHttpServer server = new QszHttpServer(port);
			server.start();
			
			logger.info("HTTP�����Ѿ���������ʼ��������["+port+"]�˿�");
		} catch (Exception e) {
			logger.error("����HTTP�������ض˿�["+port+"]��������ʧ�ܣ�", e);
			System.exit(0);
		}
	}
	
	/** �����socket */
	private ServerSocket serverSocket;
	
	/**
	 * ���췽��������һ���˿�
	 */
	private QszHttpServer (int port) throws IOException{
		// ����
		serverSocket = new ServerSocket(port);
	}
	
	/**
	 * �رշ�����
	 */
	public void shutdown(){
		if(serverSocket == null){
			return;
		}
		try {
			serverSocket.close();
		} catch (Exception e) {
			logger.warn("�������رգ�" + e.getMessage(), e);
			System.exit(0);
		}
	}

	/**
	 * �����߳�ִ��
	 */
	public void run() {
		Thread thread = Thread.currentThread();
		thread.setName("HTTP�����߳�id=" + thread.getId());
		try {
			// �����
			while(true){
				// �ȴ��ͻ�������
				Socket socket = null;
				try {
					logger.info("���ڵȴ��ͻ��˽��롭��");
					socket = serverSocket.accept();
				} catch (Exception e) {
					logger.error("�ȴ��ͻ�������ʱ����", e);
					int count = 5;
					while(count>0){
						logger.info(count+"���������������");
						count--;
						try {
							Thread.sleep(1000);
						} catch (Exception ex) {
						}
					}
					continue;
				}
				
				logger.info("����[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "]�Ŀͻ��˽���.");
				// ����ͻ�������
				ClientProcessor.process(socket);
			}
		} finally {
			try {
				serverSocket.close();
				logger.info("��⵽���б�����ļ���ɾ�����ѹر�HTTP��������");
				System.exit(0);
			} catch (IOException e) {
			}
		}
	}
	
}

