package myhttp;

public class HttpProtocolException extends Exception {
	
	private static final long serialVersionUID = 4386788867690289330L;

	public HttpProtocolException(){
		super("HTTPЭ���쳣");
	}
	
	public HttpProtocolException(String msg){
		super(msg);
	}

}
