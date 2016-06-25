package ru.spb.ipo.generator.equation;

import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.SetUtil;

public class EquationXmlGenerator extends BaseGenerator {

	public EquationXmlGenerator(Map sourceParams, Map funcParams, Map taskParams) {
		super(sourceParams, funcParams, taskParams);
	}

	public String getSourceTemplate() {
		String set = SetUtil.decart(SetUtil.numericSet("" + 0, "${result}"));		
    	String source = replace(set, getBaseSourceParameters());
    	return "<sourceSet> \n" +source + "</sourceSet>";		
	}

	public String generateDescription() {
		int nabor = Integer.valueOf((String) sourceParams.get("nabor"));
		String str = "";
		for (int i = 1; i <= nabor; i++) {
			str += "x" + i;
			if (i != nabor) {
				str+= "+";
			}
		}
		str += "=" + sourceParams.get("resultX");
		return "Найдите количество решений уравнения " + str + " в целых неотрицательных числах" +   
		(isEmptyInline() ? "" : ", если известно, что " + taskParams.get("inlineDesc") + " ") + ".";
	}

	public String getParams() {
		String genParam =   "<description-params>\n" +
        "	<param name=\"length\">\n" +
        " 		<value>${nabor}</value>\n" +
        " 	</param>\n" + 
        "	<param name=\"result\">\n" +
        " 		<value>${resultX}</value>\n" +
        " 	</param>\n" +
        "</description-params>";
		return genParam;
	}
}
