package ru.spb.ipo.generator.numbers;

import java.util.HashMap;
import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;

public class NumberXmlGenerator extends BaseGenerator {

	public NumberXmlGenerator(Map sourceParams, Map funcParams, Map taskParams) {
		super(sourceParams, funcParams, taskParams);
	}
	
	public String getSourceTemplate() {
		
		int nabor =  Integer.valueOf((String) sourceParams.get("nabor")).intValue();
		int maxDigit =  Integer.valueOf((String) sourceParams.get("maxDigit")).intValue();
		boolean isNumber = (Boolean)sourceParams.get("isNumber");
		
		String source = "";		
		source = " <sourceSet> \n <set type=\"DecartSet\"> \n";
		for (int i = 0; i < nabor; i ++) {
			if (isNumber && i == 0) {
				source +=  "<set type=\"NumericSet\" first=\"1\" last=\"" + maxDigit + "\"/>";			        
			} else {
				source +=  "<set type=\"NumericSet\" first=\"0\" last=\"" + maxDigit + "\"/>";
			}
		}
        source += "	</set>\n" +
        "</sourceSet>";
		
    	source = replace(source, getBaseSourceParameters());
    	return source;
	}

	protected Map getBaseSourceParameters() {
    	HashMap m = new HashMap();    	
    	return m;    
	}
	
	public String generateDescription() {
		int nabor =  Integer.valueOf((String) sourceParams.get("nabor")).intValue();
		int maxDigit =  Integer.valueOf((String) sourceParams.get("maxDigit")).intValue();
		boolean isNumber = (Boolean)sourceParams.get("isNumber");
		if (isNumber) {
			return "������� ���������� ���� " + nabor + "-�������  �����, ��������� �� ���� �� 0 �� " + maxDigit + 
			 (isEmptyInline() ? "." : ", ��� ������� ����� ���������: " + ((String)taskParams.get("inlineDesc")).toLowerCase() + ".");
		} else {
			return "������� ���������� ���� ������� �����, ��������� �� " + nabor + " ���� �� 0 �� " + maxDigit + 
			(isEmptyInline() ? "." : ", ��� ������� ����� ���������: " + ((String)taskParams.get("inlineDesc")).toLowerCase() + ".");
		}
    }
	
}
