package myhttp;

/**
 * �������� 
 * @author zhangyu
 */
public abstract class ContextHandler {
	
	/** Ĭ�ϵ��ַ��� */
	protected final String defaultCharset = ClientProcessor.CHARSET;
	
	private String context;
	
	/**
	 * ʹ����Ҫ�����uri·�����죬�������κ���ʽ��URL���Σ�����������POS�ύ��
	 * @param uri ������ / ��ͷ��ָ������·��
	 */
	public ContextHandler(String uri){
		this.context = uri;
	}
	
	public String getContext(){
		return context;
	}
	
	/** ����GET */
	public abstract byte[] doGet();
	
	/** ����POST */
	public abstract byte[] doPost(byte[] bytes);
	
}
