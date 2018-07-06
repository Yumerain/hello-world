package game.tank;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TankSocketHandler extends Thread {

	private Socket socket;
	private InputStream input;
	private OutputStream output;
	
	public TankSocketHandler(Socket client){
		this.socket = client; 
		InetAddress address = client.getInetAddress();
		System.out.println("�ͻ��˽��� IP:" + address.getHostAddress() + " �˿�:" + client.getPort() + " ����:" + address.getHostName());
		try {
			input = client.getInputStream();
			output = client.getOutputStream();
		} catch (IOException e) {
			System.out.println("����ʱ�쳣���رա�");
			try {
				client.close();
			} catch (Exception ex) {
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			for(;;){
				// ��ȡ��ͷ
				Head head = readHead();
				if("exit".equals(head.name)){
					break;
				}else{
					// ��ȡ����
					byte[] buff = new byte[head.length];
					System.out.println(new String(buff, "UTF-8"));
				}
			}
			socket.close();
			System.out.println(socket.toString() + "�˳�");
		} catch (IOException e) {
			System.out.println("���տͻ������ݳ���");
			e.printStackTrace();
		}	
	}
	
	private Head readHead() throws IOException{
		// ����		���ݳ���
		// |-8bytes-|-----16bytes----|
		// |--------|----------------|
		byte[] nameBuff = new byte[8];
		byte[] lengthBuff = new byte[16];
		input.read(nameBuff);
		input.read(lengthBuff);
		
		Head head = new Head();
		head.name = new String(nameBuff).trim();
		head.length = Integer.parseInt(new String(lengthBuff).trim());
		return head;
	}
	
}
