package ru.spb.ipo.generator.digits.divs;

import java.util.Map;

import ru.spb.ipo.generator.base.FuncUtil;
import ru.spb.ipo.generator.numbers.NumberXmlGenerator;

public class DivXmlGenerator extends NumberXmlGenerator {

	public DivXmlGenerator(Map<String, Object> sourceParams, Map<String, Object> funcParams, Map<String, Object> taskParams) {
		super(sourceParams, funcParams, taskParams);
	}


	public String generateDescription() {
		int nabor = Integer.valueOf((String)sourceParams.get("nabor"));
		StringBuffer def = new StringBuffer();
		for (int i = 1; i <= nabor; i++) {
			def.append("[" + i + "] ");
		}
		return "Найти все " + sourceParams.get("nabor") + " значные " 
		 + "натуральные числа, которые при изменении цифр числа по правилу: " + def + "-->" + toString((String [])funcParams.get("shift"), false) + " (в полученном числе 1 цифра не 0); " + (String)taskParams.get("inlineDesc") + ".";   
	}

	public String getSourceTemplate() {
		sourceParams.put("isNumber", new Boolean(true));		
		sourceParams.put("maxDigit", "9");
		return super.getSourceTemplate();
	}

	public String getVerifier(Map funcParams) {
    	StringBuffer sb = new StringBuffer();
    	String shiftCond = generateShift();
    	
    	sb.append("<verifier type=\"ListVerifier\">\n");
    	sb.append("<function type=\"And\">\n");
    	sb.append("<constElement>" + 1 + "</constElement>\n");    	

    	String [] shift = (String [])funcParams.get("shift");
    	
    	StringBuffer sb2 = new StringBuffer();
    	generatePosition(shift[0], sb2);
    	
    	String not0 = FuncUtil.func("Not", FuncUtil.func("Equals", sb2.toString(), FuncUtil.constElement(0)));
    	sb.append(not0 + "\n");
    	//TODO
    	String cond = (String)funcParams.get("function");    	
    	cond = replace(cond, "shift", shiftCond);
    	sb.append(cond + "\n");
    	        
		
        sb.append("</function>\n");        
        sb.append("</verifier>");          
       return sb.toString();
	}
	

	private String generateShift() {
		String [] shift = (String [])funcParams.get("shift");
		
		StringBuffer sb = new StringBuffer();
		sb.append("<function type=\"FunctionElement\">\n");    	    	
        for (int i = 0; i < shift.length; i++) {
        	generatePosition(shift[i], sb);        	
		}
		sb.append("</function>\n");
		return sb.toString();
	}
	
	private void generatePosition(String pos, StringBuffer sb) {		
    	if (pos.startsWith("[")) {
    		sb.append("    " + FuncUtil.projection(pos.substring(1,2)));
    	} else {
    		sb.append("    " + FuncUtil.constElement(pos));
    	}				
	}
	
	
}
