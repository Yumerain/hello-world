package myjava;

/**
 * ��Ƿ�
 * @author zhangyu
 */
public class Token {

	public Type tokenType;
	public Object value;
	
	public Token(Type tokenType, Object value) {
		this.tokenType = tokenType;
		this.value = value;
	}
	
	public String toString() {
		return String.format("Token(%s, %s)", tokenType, value);
	}
	
	/**
	 * ���
	 */
	enum Type {
		INTEGER,					// ���� 
		OP_PLUS, 					// �ӷ�
		OP_MINUS,					// ����
		OP_MULTIPLY,			// �˷�
		OP_DIVISION,				// ����
		EOF							// ���ݽ���
	}
}
