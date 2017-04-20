package game.tank;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class TankClient {
	
	public static void main(String[] args) {
		
		// ��������
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in, "GBK"));
			String line = null;
			while(!"exit".equals(line=reader.readLine())){
				System.out.println(line);
			}
			System.out.println("exit ����");
			// �ر�
			//server.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {reader.close();} catch (Exception ex) {}
		}
	}
	
	private InputStream input;
	
	private OutputStream output;
	
	public TankClient(){}
	
	public void start(){
		try {
			Socket skt = new Socket("127.0.0.1", 2345);
			
			input = skt.getInputStream();
			output = skt.getOutputStream();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
