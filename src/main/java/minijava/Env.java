package minijava;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.template.source.ISource;

import minijava.stat.Location;
import minijava.stat.ParseException;
import minijava.stat.ast.Define;

/**
 * Env
 * 
 * 1：解析时存放 #define 定义的模板函数
 * 2：运行时提供 #define 定义的模板函数
 * 3：每个 Template 对象持有一个 Env 对象
 */
public class Env {
	
	protected EngineConfig engineConfig;
	protected Map<String, Define> functionMap = new HashMap<String, Define>(16, 0.5F);
	
	// 代替 Template 持有该属性，便于在 #include 指令中调用 Env.addSource()
	protected List<ISource> sourceList = null;
	
	public Env() {
	}
	
	public Env(EngineConfig engineConfig) {
		this.engineConfig = engineConfig;
	}
	
	public EngineConfig getEngineConfig() {
		return engineConfig;
	}
	
//	public boolean isDevMode() {
//		return engineConfig.isDevMode();
//	}
	
	/**
	 * Add template function
	 */
	public void addFunction(Define function) {
		String fn = function.getFunctionName();
		if (functionMap.containsKey(fn)) {
			Define previous = functionMap.get(fn);
			throw new ParseException(
				"Template function \"" + fn + "\" already defined in " + 
				getAlreadyDefinedLocation(previous.getLocation()),
				function.getLocation()
			);
		}
		functionMap.put(fn, function);
	}
	
	private String getAlreadyDefinedLocation(Location loc) {
		StringBuilder buf = new StringBuilder();
		if (loc.getTemplateFile() != null) {
			buf.append(loc.getTemplateFile()).append(", line ").append(loc.getRow());
		} else {
			buf.append("string template line ").append(loc.getRow());
		}
		return buf.toString();
	}
	
//	/**
//	 * Get function of current template first, getting shared function if null before
//	 */
//	public Define getFunction(String functionName) {
//		Define func = functionMap.get(functionName);
//		return func != null ? func : engineConfig.getSharedFunction(functionName);
//	}
//	
//	/**
//	 * For EngineConfig.addSharedFunction(...) only
//	 */
//	Map<String, Define> getFunctionMap() {
//		return functionMap;
//	}
	
	/**
	 * 本方法用于在 devMode 之下，判断当前 Template 以及其下 #include 指令
	 * 所涉及的所有 ISource 对象是否被修改，以便于在 devMode 下重新加载
	 * 
	 * sourceList 属性用于存放主模板以及 #include 进来的模板所对应的
	 * ISource 对象
	 */
	public boolean isSourceListModified() {
		if (sourceList != null) {
			for (int i = 0, size = sourceList.size(); i < size; i++) {
				if (sourceList.get(i).isModified()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 添加本 Template 的 ISource，以及该 Template 使用 include 包含进来的所有 ISource
	 * 以便于在 devMode 之下判断该 Template 是否被 modified，进而 reload 该 Template
	 */
	public void addSource(ISource source) {
		if (sourceList == null) {
			sourceList = new ArrayList<ISource>();
		}
		sourceList.add(source);
	}
	
}



