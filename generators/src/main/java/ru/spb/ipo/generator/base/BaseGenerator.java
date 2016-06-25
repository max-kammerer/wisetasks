package ru.spb.ipo.generator.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Raw "skeleton" for xml-generators.
 * Generate xml task description
 * 
 * @author mike
 */
public class BaseGenerator {
	
	public static final String SET_TYPE = "setType"; 
	
	protected Map<String, Object> sourceParams;
	
	protected Map<String, Object> taskParams;
	
	protected Map<String, Object> funcParams;
	    
    public BaseGenerator(Map<String, Object> sourceParams, Map<String, Object> funcParams, Map<String, Object> taskParams) {
        this.sourceParams = sourceParams;
        this.funcParams = funcParams;
        this.taskParams = taskParams;
    }

    public String getParams() {
		return "<description-params>\n" +
				"	<param name=\"length\">\n" +
				" 		<value>${nabor}</value>\n" +
				" 	</param>\n" +
				"</description-params>";
	}
    
    public String getDescription(Map<String, Object> sourceParams, Map funcParams, Map taskParams) {
    	String genParam = getParams();
    	ArrayList images = (ArrayList)taskParams.get("images");
    	String imageStr = "";
		for (Object image : images) {
			String str = (String) image;
			imageStr += "<img>" + str + "</img>\n";
		}
    	if (imageStr.length() > 0) {
    		imageStr = "<imgs>" + imageStr + "</imgs>";
    	}
    	
    	genParam = replace(genParam, sourceParams);
    	return "<description>\n<![CDATA[" + taskParams.get("description") + "\n]]>" +
    			(imageStr.length() == 0 ? "" : imageStr) +
    			"</description>\n" + genParam;        
    }
    
    public String getSource(Map sourceParams) {        
        String source = getSourceTemplate();        
        source = replace(source, sourceParams);                
        return source;
    }
    
    public String getSourceTemplate() {
    	String source = " <sourceSet> \n <set type=\"${setType}\" length=\"${length}\"> \n" +
        "	<set type=\"DecartSet\">\n" +
        "		<set type=\"NumericSet\" first=\"${coloda}\" last=\"13\"/>\n" +
        "		<set type=\"NumericSet\" first=\"1\" last=\"4\"/>\n" +
        " 		</set>\n" +
        "	</set>\n" +
        "</sourceSet>";
    	source = replace(source, getBaseSourceParameters());
    	return source;
    }
    
    public String getVerifier(Map funcParams) {

		return "<verifier type=\"CountVerifier\">\n" +
				"	<function type=\"And\">\n" +
				"		<constElement>" + 1 + "</constElement>\n" +
						funcParams.get("function") + "\n" +
				"	</function>\n" +
				"</verifier>";
	}
    
    protected boolean isEmptyInline() {
    	return taskParams.get("inlineDesc") == null || ((String)taskParams.get("inlineDesc")).length() == 0;
    }
    
    public String generateDescription() {    	
        return "Из колоды в " + sourceParams.get("colodaDesc") + " карт вытаскивают случайным образом " + sourceParams.get("nabor") + " карт. " +
        (isEmptyInline() ? "Подсчитайте количество всех возможных наборов карт." : "Подсчитайте количество наборов, в которых " + taskParams.get("inlineDesc") + ".");
    }    
    
    public String generateXml() {
		return "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
				"<task title=\"" + wrapString((String) taskParams.get("title")) + "\">\n" +
					getDescription(sourceParams, funcParams, taskParams) + "\n" +
				" 	<mathDescription>\n" +
						getSource(sourceParams) + "\n" +
						getVerifier(funcParams) +
				" 	</mathDescription>\n" +
				"</task>";
    }
    

    protected Map<String, Object> getBaseSourceParameters() {
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("setType", "CombinationSet");    	    	
    	return m;
    }

    protected String replace(String text, Map<String, Object> values) {
		for (String key : values.keySet()) {
			Object obj = values.get(key);
			text = text.replaceAll("[$][{]" + key + "[}]", obj.toString());
		}
    	return text;
    }
    
    protected String replace(String text, String key, String value) {
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put(key, value);
    	return replace(text, m); 
    }
    
    public static String wrapString(String str) {
    	return str.replaceAll("&", "&amp;").
    				replaceAll("<", "&lt;").
			    	replaceAll(">", "&gt;").
			    	replaceAll("\"", "&quot;");			    	
    }

    public static String toString(Object [] array, boolean split) {
    	if (array != null) {
	    	StringBuffer sb = new StringBuffer();
	    	for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
				if (i < array.length - 1) {
					if (split) {
						sb.append(",");
					}
					sb.append(" ");
				}
			}
	    	return sb.toString();
    	} else {
    		return "empty";
    	}
    }
}
