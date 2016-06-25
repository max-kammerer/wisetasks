package ru.spb.ipo.generator.basket;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.FileUtil;
import ru.spb.ipo.generator.base.FuncUtil;

public class BasketXmlGenerator extends BaseGenerator {

	public static String [] ballColors = new String [] {"белый", "черный", "синий", "красный", "зеленый"};
	
	public static Color [] colors = new Color [] {Color.WHITE, new Color(80, 80, 80), new Color(0, 100, 250), Color.RED, Color.GREEN};
	
	public BasketXmlGenerator(Map source, Map func, Map task) {
		super(source, func, task);
	}

    public String getParams() {
    	String genParam =    "<description-params>\n" +
        "	<param name=\"length1\">\n" +
        " 		<value>${length1x}</value>\n" +
        " 	</param>\n" + 
        (isSingle() ?  "" : "	<param name=\"length2\">\n" +
        " 		<value>${length2x}</value>\n" +
        " 	</param>\n") +
        "	<param name=\"toFind\">\n" +
        " 		<value>${toFindx}</value>\n" +
        " 	</param>\n" +
        "</description-params>";
    	return genParam;
    }
	
	public String generateDescription() {
		String res = "";
		if (!isSingle()) {
			res = "Имеются две урны A и B , в урне A находится " + toDesc((ComplexElement []) sourceParams.get("basket1")) + " шаров, в урне B - " + toDesc((ComplexElement []) sourceParams.get("basket2")) + ".";  
		} else {
			res = "В урне A находится " + toDesc((ComplexElement []) sourceParams.get("basket1")) + " шаров.";
		}
		res += " С какой вероятностью из урны A можно " + (isCont() ? "последовательно" : "одновременно") + " вытащить" + taskParams.get("inlineDesc") + ".";
		return res;
		
	}

	
	public String generateXml() {
		int length1 = toSize((ComplexElement []) sourceParams.get("basket1"));
		int length2 = toSize((ComplexElement []) sourceParams.get("basket2"));		
		int toFind = toSize((ComplexElement []) funcParams.get("toFind"));
		
		sourceParams.put("length1x", length1);
		sourceParams.put("length2x", length2);
		sourceParams.put("toFindx", toFind);
		
		return super.generateXml();
	}

	private boolean isCont() {
		return (Boolean)funcParams.get("isCont");
	}
	
	private boolean isSingle() {
		return (Boolean)sourceParams.get("isSingle");
	}

	private String toDesc(ComplexElement [] ces) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ces.length; i++) {
			sb.append(ces[i].toString());
			if (i < ces.length - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	private int toSize(ComplexElement [] ces) {
		int size = 0;
		for (int i = 0; i < ces.length; i++) {
			size += ((BasketBallComplexElement)ces[i]).numbers;			
		}
		return size;
	}
	
    public String getSource(Map sourceParams) {
    	String source = " <sourceSet> \n <set type=\"" + (isCont() ? "LayoutSet" : "CombinationSet") + "\" length=\"${toFind}\"> \n"+
    	"	<set type=\"EnumerationSet\">\n";
    	
    	ComplexElement [] ces1 = (ComplexElement []) sourceParams.get("basket1");
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	
    	for (int i = 0; i < ces1.length; i++) {
    		BasketBallComplexElement ce = (BasketBallComplexElement)ces1[i];
    		for (int j = 0; j < ce.numbers; j++) {
//    			source += "<constElement>";
	    		source += "    <constElement>" + ce.color + "</constElement>";
//	    		source += "    <constElement>" + j + "</constElement>";
//	    		source += "</constElement>";
    		}
		}
    	source += "	</set>\n";
    	source += "	</set>\n";
    	source +="</sourceSet>";                
        source = replace(source, sourceParams);                
        return source;
    }
	
    
    public String getVerifier(Map funcParams) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("<verifier type=\"CountVerifier\" normalize=\"" + (isCont() ? "A" : "C") + "(${length1} ,${toFind})" + "\">\n");
    	sb.append("<function type=\"And\">\n");
    	sb.append("<constElement>" + 1 + "</constElement>\n");    	

    	ComplexElement [] basket1 = (ComplexElement []) sourceParams.get("basket1");
    	
    	StringBuffer toFindString = new StringBuffer("");    	
    	ComplexElement [] toFind = (ComplexElement []) funcParams.get("toFind");
//    	for (int i = 0; i < toFind.length; i++) {
//    		BasketBallComplexElement ce = (BasketBallComplexElement)toFind[i];
//    		for (int j = 0; j < ce.numbers; j++) {
////    			String left = FuncUtil.projection("1", FuncUtil.projection("${length1} - " + i));
//    			String left = FuncUtil.projection("${length1} - " + i);
//    			String right = FuncUtil.constElement(ce.color);
//    			condition.append(FuncUtil.equals(left, right));
//    		}
//		}
    	
    	for (int i = 0; i < toFind.length; i++) {
    		BasketBallComplexElement ce = (BasketBallComplexElement)toFind[i];
    		for (int j = 0; j < ce.numbers; j++) {
    			toFindString.append(FuncUtil.constElement(ce.color));    			
    		}
		}
    	
    	
    	String condition = "<function type=\"" +(isCont() ? "Equals" : "Like") + "\">\n" +
    		FuncUtil.cse() + 
    		FuncUtil.constElement(toFindString.toString()) +
		"</function>\n";
    	
//    	for (int i = 0; i < basket1.length; i++) {
//    		BasketBallComplexElement ce = basket1[i];
//    		String str = FuncUtil.for1("i", "1", "${length1}", "1", "");
//		}
    	
    	sb.append(condition + "\n");
        sb.append("</function>\n");        
        sb.append("</verifier>");          
        return sb.toString();
    }    
}
