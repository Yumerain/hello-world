package game.tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class TankServer extends Thread{
	
	public static void main(String[] args) {
		TankServer server = new TankServer();
		// ���������߳�
		server.start();
		// ��������
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in, "GBK"));
			String line = null;
			while(!"stop".equals(line=reader.readLine())){
				System.out.println(line);
			}
			System.out.println("stop ����");
			// �ر�
			server.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {reader.close();} catch (Exception ex) {}
		}
	}
	
	private static Logger logger = Logger.getLogger("mainLogger");
	
	private ServerSocket serverSocket;
	
	public TankServer(){
		try {
			serverSocket = new ServerSocket(2345);
		} catch (IOException e) {
			logger.error("����������ʧ�ܣ�"+e.getMessage(), e);
			System.exit(1);
		}
	}
	
	public void shutdown() throws IOException{
		serverSocket.close();
	}
	
	public void run(){
		// ֻ����δ�ر�ʱִ��
		while(!serverSocket.isClosed()){
			try {
				logger.info("�ȴ��ͻ��˽��롭��");
				Socket client = serverSocket.accept();
				//new SocketHandler(client).start();
			} catch (IOException e) {
				logger.error("����ʱ���� I/O ����:"+e.getMessage(), e);
			}
		}
	}

}
