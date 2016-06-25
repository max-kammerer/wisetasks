package ru.spb.ipo.generator.digits.mods;

import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;

public class ModXmlGenerator extends BaseGenerator {


	public ModXmlGenerator(Map source, Map func, Map task) {
		super(source, func, task);
	}

	public String getVerifier(Map funcParams) {
		StringBuffer sb = new StringBuffer();
    	sb.append("<verifier type=\"AnswerVerifier\">\n");
    	sb.append("<function type=\"Parser\" exp=\"" + funcParams.get("expression")  + "\" mod=\"" + funcParams.get("mod") + "\">\n");
    	sb.append("</function>\n");        
        sb.append("</verifier>");          
       return sb.toString();
	}

	public String generateDescription() {
		return " акой остаток дает число " + funcParams.get("expression") + " при делении на " + funcParams.get("mod") + "?";
	}

	
	public String getParams() {		
		return "";
	}

	public String getSource(Map sourceParams) {		
		return "";
	}
}
