package myhttp;

/**
 * HTTP������
 */
public class StartLine {
	
	private static final HttpProtocolException exception = new HttpProtocolException("������ʼ�в�����Э��");
	
	private static final String V11 = " HTTP/1.1";
	private static final String V10 = " HTTP/1.0";
	
	private String method;
	private String uri;
	private String version;
	
	/**
	 * �����������ַ�����
	 * @param line
	 * @return
	 * @throws HttpProtocolException 
	 */
	public static StartLine parse(String line) throws HttpProtocolException{
		StartLine sl = new StartLine();
		// ����֪��[GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,CONNECT]�����������7���ַ�+1���ո��ڲ��ҵ�һ���ո�
		int index = -1;	// ��һ���ո�Ϊ��ʡʱ�䣬���ֻ��8�����ڲ���
		for (int i = 0; i < 8; i++) {
			if(line.charAt(i) == ' '){
				index = i;
				break;
			}
		}
		if(index<=0  || index>=8){
			throw exception;	// ���󲻷���HTTPЭ��
		}
		//�����г�������>(index+1)+SUFFIX.length()���пռ��URI������
		if(line.length() <= index+1+V11.length()){
			throw exception;	// ���󲻷���HTTPЭ��
		}
		// ���HTTP�汾�����һ���ո�֮�������
		if(line.charAt(line.length() - V11.length() - 1)==' '){
			throw exception;	// ���󲻷���HTTPЭ��
		}
		String ver = line.substring(line.length()-V11.length(), line.length());
		if(V11.equals(ver)){
			sl.version = V11;
		} else if(V10.equals(ver)){
			sl.version = V10;
		} else {
			throw exception;	// ���󲻷���HTTPЭ��
		}
		sl.method = line.substring(0, index);
		sl.uri = line.substring(index+1, line.length()-V11.length());
		return sl;
	}
	
	public String getMethod() {
		return method;
	}

	public String getUri() {
		return uri;
	}
	
	public String getVersion(){
		return version;
	}
	
	@Override
	public String toString() {
		return "method:[" + method + "] uri:[" + uri + "] version:[" + version + "]";
	}
	
}

