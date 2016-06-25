package ru.spb.ipo.generator.word;

import java.util.Collections;
import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;

public class WordXmlGenerator extends BaseGenerator {

	public WordXmlGenerator(Map sourceParams, Map funcParams, Map taskParams) {
		super(sourceParams, funcParams, taskParams);
	}

    public String getParams() {
		return "<description-params>\n" +
				"	<param name=\"length\">\n" +
				" 		<value>${nabor}</value>\n" +
				" 	</param>\n" +
				"	<param name=\"lengthMod2\">\n" +
				" 		<value>${half-length}</value>\n" +
				" 	</param>\n" +
				"</description-params>";
	}
	
	public String getSourceTemplate() {
		int nabor = Integer.valueOf((String) sourceParams.get("nabor"));
		String source = "";
		if ((Boolean) sourceParams.get("setType-template")) {
			source = " <sourceSet> \n <set type=\"DecartSet\" length=\"${length}\"> \n";
			for (int i = 0; i < nabor; i ++) {
				source +="	<set type=\"EnumerationSet\">\n" +
				    	"${set-elements}" +
				        " 		</set>\n";
			}
	        source += "	</set>\n" +
	        "</sourceSet>";
		} else {
			source = " <sourceSet> \n <set type=\"LayoutSet\" length=\"${length}\"> \n" +
	        "	<set type=\"EnumerationSet\">\n" +
	    	"${set-elements}" +
	        " 		</set>\n" +
	        "	</set>\n" +
	        "</sourceSet>";
		}
    	source = replace(source, getBaseSourceParameters());
    	return source;
	}

	protected Map<String, String> getBaseSourceParameters() {
    	//m.put("setType", "LayoutSet");
    	return Collections.emptyMap();
	}
	
	public String generateDescription() {
		
        return "Задан алфавит A = " + taskParams.get("alphabit") + " (буквы в словах" + (isMulti() ? " " : " не ") +  "могут повторяться). Подсчитайте количество всeх слов длины " + sourceParams.get("nabor") + " в алфавите А" +
        		(isEmptyInline() ? "." : ", для которых верно следующее: " + ((String)taskParams.get("inlineDesc")).toLowerCase() + ".");
    }
	
	boolean isMulti() {
		return (Boolean) sourceParams.get("setType-template");
	}
	
}
