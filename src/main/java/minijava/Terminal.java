package minijava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 命令行接受用户输入
 * @author zhangyu
 */
public class Terminal {
	
	public static void main(String[] args) {
		new Terminal().start();
	}

	private boolean multiLine = false;
	private StringBuilder buff = new StringBuilder();
	private BufferedReader reader;
	private Env env = new Env();

	public Terminal(){
		try {
			reader = new BufferedReader(new InputStreamReader(System.in, System.getProperty("file.encoding")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void start(){
		try {
			String line = null;
			do {
				// 多行模式、单行模式显示不同
				if(multiLine)
					System.out.print(">>"); 
				else
					System.out.print(">");
				
				// 接收输入
				line = reader.readLine();
				
				// 单行模式下退出标识符
				if (!multiLine && "exit".equals(line)) {
					break;
				}
				
				// 切换到多行模式下
				if(">>".equals(line)){
					multiLine = true;
					continue;
				}
				
				// 切换到单行模式下
				if(">".equals(line)){
					multiLine = false;
					continue;
				}
				
				if(multiLine){
					if("<<".equals(line)){
						env.resolve(buff.toString());	// 多行模式解发后解析
						buff.setLength(0);
					}else{
						buff.append(line).append(System.lineSeparator());
					}					
				}else{
					env.resolve(line);							// 单行模式立即解析
				}
			} while (line != null);
			System.out.println("== Bye ==");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
