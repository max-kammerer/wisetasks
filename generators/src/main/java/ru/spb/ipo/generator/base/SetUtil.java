package ru.spb.ipo.generator.base;

import java.util.ArrayList;

public class SetUtil {

	public static String decart(String origin) {
		String source = "	<set type=\"DecartSet\">\n" +
		"<for name=\"di\" first=\"1\" last=\"${length}\" inc=\"1\">\n"  +
		origin +
        "</for>\n" +
        "	</set>\n" ;
		return source;
	}
	
	public static String numericSet(String first, String last) {
        String s = "<set type=\"NumericSet\" first=\"" + first + "\" last=\"" +  last + "\"/>\n";
		return s;
	}
        
    public static String enumerationSet(ArrayList list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<set type=\"EnumerationSet\">\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append("<constElement>"+list.get(i)+"</constElement>\n");
        }
        sb.append("</set>\n");
        return sb.toString();
    }

   public static String constElement(ArrayList list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<constElement>\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append("<constElement>"+list.get(i)+"</constElement>\n");
        }
        sb.append("</constElement>\n");
        return sb.toString();
    }

    public static String Substitution(String content, String dim) {
        StringBuilder sb = new StringBuilder();
        sb.append("<set type=\"SubstitutionSet\" dim=\""+dim+"\">\n");
        sb.append(content);
        sb.append("</set>\n");
        return sb.toString();
    }

    public static String permutationWithRepetition(ArrayList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<set type=\"PermutationWithRepetitionSet\">\n");
        sb.append("<set type=\"EnumerationSet\">\n");
        for (int i = 0; i < list.size(); i++) {
            Integer colCount = list.get(i);
            for (int j = 0; j < colCount; j++)
                sb.append("<constElement>"+(i+1)+"</constElement>");
        }
        sb.append("</set>\n");
        sb.append("</set>\n");
        return sb.toString();
    }
}
