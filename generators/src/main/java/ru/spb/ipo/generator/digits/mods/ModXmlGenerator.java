package ru.spb.ipo.generator.digits.mods;

import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;

public class ModXmlGenerator extends BaseGenerator {


	public ModXmlGenerator(Map<String, Object> sourceParams, Map<String, Object> funcParams, Map<String, Object> taskParams) {
		super(sourceParams, funcParams, taskParams);
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
		return "����� ������� ���� ����� " + funcParams.get("expression") + " ��� ������� �� " + funcParams.get("mod") + "?";
	}

	
	public String getParams() {		
		return "";
	}

	public String getSource(Map sourceParams) {		
		return "";
	}
}
