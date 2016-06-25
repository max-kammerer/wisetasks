/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors;

import java.util.ArrayList;
import java.util.Map;
import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.SetUtil;

/**
 *
 * @author Admin
 */
public class BeadsXmlGenerator extends BaseGenerator {
    public BeadsXmlGenerator(Map sourceParams, Map funcParams, Map taskParams) {
		super(sourceParams, funcParams, taskParams);
    }
    public String generateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Сколько различных бус можно составить");
        Integer taskType = (Integer)sourceParams.get("taskType");
        if (taskType == 1) {
            sb.append(" из "+sourceParams.get("beadsLength")+" бусин");
            sb.append(", окрашенных в "+sourceParams.get("colors")+" различных ");
            if ((Integer)sourceParams.get("colors") < 5) {
                sb.append("цвета.");
            }
            else {
                sb.append("цветов.");
            }
        }
        else {
            sb.append(", если имеется ");
            sb.append(taskParams.get("inlineDesc")+".");
        }
        sb.append(" Бусы считаются одинаковыми, если сдвигами бусин по нитке и различными" +
                " поворотами в пространстве их можно сделать неотличимыми.");
        return sb.toString();
    }
    
    public String getSourceTemplate() {
	StringBuilder set = new StringBuilder();
        set.append("<sourceSet> \n<set type=\"DistinctSet\">\n");
        
        Integer taskType = (Integer)sourceParams.get("taskType");
        if (taskType == 1) {
            set.append(SetUtil.decart(SetUtil.numericSet("1", "${colors}")));
        }
        else {
            ArrayList<Integer> cList = (ArrayList<Integer>)sourceParams.get("cList");
            set.append(SetUtil.permutationWithRepetition(cList));
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        Integer length = (Integer)sourceParams.get("beadsLength");
        for (int i = 0; i < length; i++)
            list.add(i+1);
        // добавление перестановки поворота бус в своей плоскости
        ArrayList<Integer> roundList = new ArrayList<Integer>();
        roundList.add(Integer.valueOf(length));
        for (int i = 0; i < length-1; i++) {
            roundList.add(Integer.valueOf(i+1));
        }
        StringBuilder enumeration = new StringBuilder();
        enumeration.append(SetUtil.constElement(roundList));
        // переворачивание бус
        ArrayList<Integer> turnList = new ArrayList<Integer>();
        for (int i = 0; i < list.size()/2+1; i++) {
            turnList.add(list.get(list.size()/2-i));
        }
        for(int i = list.size()-1; i > list.size()/2; i--)
            turnList.add(list.get(i));
        enumeration.append(SetUtil.constElement(turnList));
        set.append(SetUtil.Substitution(enumeration.toString(), "${length}"));
        set.append("</set>\n</sourceSet>");
    	String source = replace(set.toString(), getBaseSourceParameters());
        return set.toString();	
    }
    
    public String getParams() {
	String genParam =   "<description-params>\n" +
        "	<param name=\"length\">\n" +
        "		<value>${beadsLength}</value>\n" +
        "	</param>\n" + 
        "</description-params>";
		return genParam;
    }
    
    public String getVerifier(Map funcParams) {
        StringBuffer sb = new StringBuffer();
        sb.append("<verifier type=\"SimpleVerifier\">\n");
        sb.append("<function type=\"Equals\">\n");
        sb.append("<constElement>" + 1 + "</constElement>\n");    	
        sb.append("<constElement>" + 1 + "</constElement>\n");    	
        sb.append("</function>\n");
        sb.append("</verifier>\n");      
        return sb.toString();
    }
}
