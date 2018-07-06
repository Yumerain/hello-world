package minijava;

/**
 * ��Ƿ�
 * @author zhangyu
 */
public class Token {

	public Type type;
	public Object value;
	
	public Token(Type type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	public String toString() {
		return String.format("Token(%s, %s)", type, value);
	}
	
	/**
	 * ���
	 */
	enum Type {
		INTEGER,				// ���� 
		OP_PLUS, 				// �ӷ�
		OP_MINUS,				// ����
		OP_MULTIPLY,		// �˷�
		OP_DIVISION,			// ����
		EOF							// ���ݽ���
	}
}
