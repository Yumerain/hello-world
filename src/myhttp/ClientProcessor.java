package myhttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ClientProcessor extends Thread {

	private static Logger logger = Logger.getLogger(ClientProcessor.class);
	
	private static HttpProtocolException headExcpt = new HttpProtocolException("����ͷ��������Э��");
	
	/** HTTPЭ�鶨��Ļ��з� */
	private static final String CRLF = "\r\n";
	
	/** ����ͷÿ������ַ����� */
	private static final int HEADLINE_MAX = 2048;
	
	/** ����ͷ������ */
	private static final int HEADCOUNT_MAX = 100;
	
	/** ���������� */
	public static final String CHARSET = "GBK";
	

	/** �����Ĵ����� */
	private Map<String, ContextHandler> contextHandlerMap = new HashMap<String, ContextHandler>();
	
	/** HTTP����ͷ */
	private Map<String, String> headers = new HashMap<String, String>(10);
	
	/** HTTP״̬ */
	private int statusCode = 200;
	
	private String uri;
	private String method;
	
	/** POST���͹��������� */
	private byte[] content = new byte[0];
	
	/** �������߳��첽���� */
	public static void process(Socket socket) {
		Thread thread = new ClientProcessor(socket);
		thread.setName("�����߳�id=" + thread.getId());
		thread.start();
	}

	/** ���ӵĿͻ��� */
	private Socket socket;

	/**
	 * ���췽��
	 * @param skt
	 */
	public ClientProcessor(Socket skt){
		this.socket = skt;
		// TODO: ADD handler1
		//addContextHandler(new ContextHandler("/url_1"));
		// TODO: ADD handler2
		//addContextHandler(new ContextHandler("/url_2"));
	}
	
	/** ��������Ĵ����� */
	public void addContextHandler(ContextHandler ctx){
		contextHandlerMap.put(ctx.getContext(), ctx);
	}
	
	/**
	 * ִ�д���
	 */
	public void run() {
		logger.info("��ʼ��������[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "]�ͻ�������.");
		try {
			socket.setTcpNoDelay(true);
			socket.setSoTimeout(1 * 60 * 1000);// ���ö�ȡ��ʱ��1����
			
			// ��������
			request(socket.getInputStream());
			
			// ������Ӧ
			response(socket.getOutputStream());
			
			logger.info("����[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "]����������Ӧ["+statusCode+"].");
		} catch (Exception e) {
			logger.error("��������[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "]�Ŀͻ���socket����.", e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	private void request(InputStream input) {
		try {
			// ��ȡ����ʼ�С���Ҳ��HTTP��ʼ�ĵ�һ��
			String startLine = readHeadLine(input);
			StartLine sl = StartLine.parse(startLine);
			this.uri = sl.getUri();
			this.method = sl.getMethod();
			
			// ��ȡ�����е�header
			readAllHeaders(input);
			
			// �����GET������ֱ�ӽ���
			if("GET".equals(method)){
				return;
			}
			// �����POST�������ȡPOST���ݣ�ͬʱҪ������ͷ����Content-Length
			if("POST".equals(sl.getMethod())){
				if(!headers.containsKey("Content-Length")){
					statusCode = 400;	// POSTȱ�����ͷ�� 
					return;
				}
				int length = Integer.parseInt(headers.get("Content-Length"));
				if(length <= 0){
					return;
				}
				ByteArrayOutputStream mem = new ByteArrayOutputStream(length);
				while(true){
					int bt = input.read();
					if(bt == -1)break;
					mem.write(bt);
					// ���ֻ��ȡContent-Length�ĳ���
					if(mem.size()==length){
						break;
					}
				}
				// ���POST�����ݲ�����
				if(mem.size()<length){
					statusCode = 400;
					return;
				}
				// POST������
				content = mem.toByteArray();
				return;
			}
			statusCode = 405;	// GET��POS֮��������ݲ�����
		} catch (HttpProtocolException e) {
			statusCode = 400;	// HTTPЭ���������
			logger.error("", e);
		} catch (IOException e) {
			statusCode = 500;	// �������ڲ�����
			logger.error("", e);
		} catch (Exception e) {
			statusCode = 500;	// �������ڲ�����
			logger.error("", e);
		}
	}
	
	/** ���������ж�ȡͷ���� */
	private String readHeadLine(InputStream is) throws IOException, HttpProtocolException {
		StringBuilder buff = new StringBuilder(256);		// ͨ��HTTPЭ������ÿ��200���ַ���
		while(true){
			int bt = is.read();
			if(bt == -1)break;
			buff.append((char)bt);
			// ���ݳ��ȼ�飺�������http header������Ҫ����ÿ�е���󳤶ȣ�Ԥ���Ƿ�����
			if(buff.length()>HEADLINE_MAX){
				logger.error("����ͷ���������ݳ����趨���ֵ��" + buff.toString());
				throw headExcpt;
			}
			// �ж��Ƿ����[CRLF]���У��ϸ���֤����ͬʱ����
			if(buff.charAt(buff.length()-1)=='\n' && buff.length()>=2 && buff.charAt(buff.length()-2)=='\r'){
				// ��ȡ��CRLF����һ�н���
				break;
			}
		}
		// ɾ��CRLF
		buff.setLength(buff.length()-2);
		// ���ز������е��ַ�
		return buff.toString();
	}
	
	/** ��ȡ����HTTP��ͷ */
	private void readAllHeaders(InputStream is) throws IOException, HttpProtocolException {
		int count = HEADCOUNT_MAX;
		while(count>0){
			String line = readHeadLine(is);
			if(line.isEmpty()){
				break;	// ����˵��ͷ��������
			}
			int splitIndex = line.indexOf(": ");		// �ϸ���һ��ð�š�һ���ո�
			if(splitIndex == -1){
				logger.error("������HTTPЭ���ͷ����" + line);
				throw headExcpt;
			}
			headers.put(line.substring(0, splitIndex), line.substring(splitIndex+2));
			count--;
		}
	}

	private void response(OutputStream output) throws IOException {
		if(statusCode != 200){
			notFoundRequest(output);
			return;
		}
		// Ѱ�Ҷ�Ӧ�������Ĵ�����
		ContextHandler contextHandler = contextHandlerMap.get(uri);
		if(contextHandler == null){
			notFoundRequest(output);
			return;
		}
		
		byte[] result;
		// ����֧��GET��POST
		if("GET".equals(method)){
			result = contextHandler.doGet();
		}else if("POST".equals(method)){
			result = contextHandler.doPost(content);
		}else{
			notFoundRequest(output);
			return;
		}
		StringBuilder buff = new StringBuilder();
		buff.append("HTTP/1.1 200 OK").append(CRLF);
		//Date: Fri, 20 Nov 2015 10:20:47 GMT
		buff.append("Content-Type: text/plain; charset=" + CHARSET).append(CRLF);
		buff.append("Server: Java-Transformers V1.1").append(CRLF);
		buff.append("Connection: close").append(CRLF);
		buff.append("Content-Length: ").append(result.length).append(CRLF);
		buff.append(CRLF);
		// ���head��headֻ����Ӣ����ĸ�����ַ������ر�Ҫ��
		output.write(buff.toString().getBytes(CHARSET));
		output.flush();
		// �������
		output.write(result);
		output.flush();
	}
	
	/** ��֧�ֵ��������Ӧ:404 Not Found */
	private void notFoundRequest(OutputStream os) throws IOException{
		String notice = "404 Not Found";
		StringBuilder buff = new StringBuilder();
		buff.append("HTTP/1.1 404 Not Found").append(CRLF);
		buff.append("Server: Java-Transformers V1.0").append(CRLF);
		buff.append("Connection: close").append(CRLF);
		buff.append("Content-Type: text/plain").append(CRLF);
		buff.append("Content-Length: ").append(notice.length()).append(CRLF);
		buff.append(CRLF);
		buff.append(notice);
		os.write(buff.toString().getBytes(CHARSET));
		os.flush();
	}
}
