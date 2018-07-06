package myjava;

public class Interpreter {
	
	protected Lexer lexer;
	
	protected Token currToken;
	
	public Interpreter(Lexer lexer){
		this.lexer = lexer;
		this.currToken = lexer.nextToken();
	}
	
	public void error() {
		throw new RuntimeException("��Ч���﷨");
	}
	
	public void eat(Token.Type tokenType) {
		if (currToken.tokenType == tokenType) {
			currToken = lexer.nextToken();
		} else {
			error();
		}
	}
	
	public Object factor() {
		// �ֽ׶�Ĭ�Ϲ̶�����
		Token token = this.currToken;
		eat(Token.Type.INTEGER);
		return token.value;
	}
	
	
	/** 
	 * �ú���У�飨��֤����Ƿ������Ƿ���Ԥ������һ�£����磬INTEGER -> PLUS -> INTEGER��
	 * �ڽṹȷ������������Ӻ���߱�Ƿ���ֵ�����ұ߱�Ƿ�ֵ��ӣ����ɣ����ʽ�ģ������
	 * �������ͽ��㴫���������ı��ʽ���ɹ�������������ʽ�Ľ���� 
	 */
	public Object expr() {
		Object result = factor();
		while (currToken.tokenType == Token.Type.OP_MULTIPLY || currToken.tokenType == Token.Type.OP_DIVISION) {
			if (currToken.tokenType == Token.Type.OP_MULTIPLY) {
				eat(Token.Type.OP_MULTIPLY);
				result = Integer.parseInt(String.valueOf(result)) * Integer.parseInt(String.valueOf(factor()));
			}
			else if (currToken.tokenType == Token.Type.OP_DIVISION) {
				eat(Token.Type.OP_MULTIPLY);
				result = Integer.parseInt(String.valueOf(result)) * Integer.parseInt(String.valueOf(factor()));
			}
		}
		return result;
	}
	
}
