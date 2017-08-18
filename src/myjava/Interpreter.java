package myjava;

public class Interpreter {
	
	public String text;
	public int pos = 0;
	public char currChar;
	
	public Interpreter(String text){
		this.text = text;
		this.currChar = text.charAt(pos);
	}
	
	public Token nextToken() {
		while(pos < text.length()){
			
			currChar = text.charAt(pos++);
			
			// �հ��ַ�
			if (currChar == ' ' || currChar == '\t' || currChar == '\r' || currChar == '\n') {
				continue;
			}
			
			// ����
			if (currChar >= '0' && currChar <= '9') {
				return new Token(Token.Type.INTEGER, Integer.valueOf(String.valueOf(currChar)));
			}
			
			// ���������
			if (Operator.isMathOperator(currChar)) {
				switch (currChar) {
				case '+': 	return new Operator(Token.Type.OP_PLUS, currChar);
				case '-': 	return new Operator(Token.Type.OP_MINUS, currChar);
				case '*': 	return new Operator(Token.Type.OP_MULTIPLY, currChar);
				case '/': 	return new Operator(Token.Type.OP_DIVISION, currChar);
				}
			}
			
			throw new RuntimeException("Error parsing input");
		}
		
		// ����
		return new Token(Token.Type.EOF, null);
	}
	
	
	/** 
	 * �ú���У�飨��֤����Ƿ������Ƿ���Ԥ������һ�£����磬INTEGER -> PLUS -> INTEGER��
	 * �ڽṹȷ������������Ӻ���߱�Ƿ���ֵ�����ұ߱�Ƿ�ֵ��ӣ����ɣ����ʽ�ģ������
	 * �������ͽ��㴫���������ı��ʽ���ɹ�������������ʽ�Ľ���� 
	 */
	public Object expr() {
		Token left = nextToken();
		
		Operator op = (Operator)nextToken();
		
		Token right = nextToken();
		return op.eval(left, right);
	}
	
}
