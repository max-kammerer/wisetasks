package ru.spb.ipo.generator.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Raw "skeleton" for xml-generators.
 * Generate xml task description
 * 
 * @author mike
 */
public class BaseGenerator {
	
	public static final String SET_TYPE = "setType"; 
	
	protected Map sourceParams;
	
	protected Map taskParams;
	
	protected Map funcParams;
	    
    public BaseGenerator(Map sourceParams, Map funcParams, Map taskParams) {
        this.sourceParams = sourceParams;
        this.funcParams = funcParams;
        this.taskParams = taskParams;
    }

    public String getParams() {
    	String genParam =    "<description-params>\n" +
        "	<param name=\"length\">\n" +
        " 		<value>${nabor}</value>\n" +
        " 	</param>\n" + 
        "</description-params>";
    	return genParam;
    }
    
    public String getDescription(Map sourceParams, Map funcParams, Map taskParams) {
    	String genParam = getParams();
    	ArrayList images = (ArrayList)taskParams.get("images");
    	String imageStr = "";
    	for (int i = 0; i < images.size(); i++) {
    		String str  = (String)images.get(i);
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
    	StringBuffer sb = new StringBuffer();
    	sb.append("<verifier type=\"CountVerifier\">\n");
    	sb.append("<function type=\"And\">\n");
    	sb.append("<constElement>" + 1 + "</constElement>\n");    	
        
    	sb.append(funcParams.get("function") + "\n");        
		
        sb.append("</function>\n");        
        sb.append("</verifier>");          
       return sb.toString();
    }
    
    public boolean isEmptyInline() {
    	return taskParams.get("inlineDesc") == null || ((String)taskParams.get("inlineDesc")).length() == 0;
    }
    
    public String generateDescription() {    	
        return "Из колоды в " + sourceParams.get("colodaDesc") + " карт вытаскивают случайным образом " + sourceParams.get("nabor") + " карт. " +
        (isEmptyInline() ? "Подсчитайте количество всех возможных наборов карт." : "Подсчитайте количество наборов, в которых " + taskParams.get("inlineDesc") + ".");
    }    
    
    public String generateXml() {        
        StringBuffer str = new StringBuffer("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n");
        str.append("<task title=\"" + wrapString((String)taskParams.get("title")) + "\">\n");
        str.append(getDescription(sourceParams, funcParams, taskParams)+ "\n");
        str.append(" <mathDescription>\n");
        str.append(getSource(sourceParams) + "\n");
        str.append(getVerifier(funcParams));
        
        str.append(" </mathDescription>\n");
        str.append(" </task>");
        return str.toString();                
    }
    

    protected Map getBaseSourceParameters() {
    	HashMap m = new HashMap();
    	m.put("setType", "CombinationSet");    	    	
    	return m;
    }

    protected String replace(String text, Map values) {
    	Iterator it = values.keySet().iterator();
    	while (it.hasNext()) {
    		String key =  (String) it.next();
    		Object obj = values.get(key);
    		text = text.replaceAll("[$][{]" + key + "[}]", obj.toString());
    	}
    	return text;
    }
    
    protected String replace(String text, String key, String value) {
    	Map m = new HashMap();
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
