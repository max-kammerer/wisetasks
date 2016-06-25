/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors;

import ru.spb.ipo.generator.colors.figures.*;
import java.util.ArrayList;
import java.util.Map;
import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.SetUtil;
import ru.spb.ipo.generator.colors.figures.Figure;
import ru.spb.ipo.generator.colors.figures.Triangle;

/**
 *
 * @author Admin
 */
public class PolygonXmlGenerator extends BaseGenerator{
    Figure figure;
    public PolygonXmlGenerator(Map<String, Object> sourceParams, Map<String, Object> funcParams, Map<String, Object> taskParams) {
		super(sourceParams, funcParams, taskParams);
    }
    @Override
    public String generateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Сколькими способами можно раскрасить ");
        sb.append(sourceParams.get("polygonText"));
        Integer taskType = (Integer)sourceParams.get("taskType");
        if (taskType == 1) {
            sb.append(" "+sourceParams.get("colors")+" красками.");
        }
        else {
            sb.append(", если среди них будет ");
            sb.append(taskParams.get("inlineDesc")+".");
        }
        sb.append(" Раскраски считаются одинаковыми, если поворотом фигуры в " +
                "пространстве одна раскраска получается из другой.");
        return sb.toString();
    }
    
    @Override
    public String getParams() {
        String figureParam = (String)sourceParams.get("polygon");
        String brushParam = (String)sourceParams.get("whatToBrush");
        if (figureParam.equals("Равнобедренный треугольник"))
            figure = new Triangle(brushParam);
        else if (figureParam.equals("Прямоугольник"))
            figure = new Rectangle(brushParam);
        else if (figureParam.equals("Ромб"))
            figure = new Romb(brushParam);
        else {
            String corners = (String)sourceParams.get("corners");
            figure = new Polygon(brushParam,Integer.valueOf(corners));
        }
        sourceParams.put("elementsCount", Integer.valueOf(figure.getDim()));
        StringBuilder sb = new StringBuilder();
        sb.append("<description-params>\n" +
        "	<param name=\"length\">\n" +
        "		<value>${elementsCount}</value>\n" +
        "	</param>\n");
        Integer taskType = (Integer)sourceParams.get("taskType");
        if (taskType == 1) {
            sb.append("	<param name=\"colorCount\">\n" +
        "		<value>${colors}</value>\n" +
        "	</param>\n" + 
        "</description-params>");
        }
        else {
            sb.append("</description-params>");
        }
        return sb.toString();
    }
    
    @Override
    public String getSourceTemplate() {
	StringBuilder set = new StringBuilder();
        set.append("<sourceSet> \n<set type=\"DistinctSet\">\n");
        Integer taskType = (Integer)sourceParams.get("taskType");
        if (taskType == 1)
            set.append(SetUtil.decart(SetUtil.numericSet("1", "${colorCount}")));
        else {
            ArrayList<Integer> cList = (ArrayList<Integer>)sourceParams.get("cList");
            set.append(SetUtil.permutationWithRepetition(cList));
        }
        StringBuilder enumeration = new StringBuilder();
        int n = figure.getPermCount();
        ArrayList<Integer> perm;
        for (int i = 0; i < n; i++) {
            perm = figure.getPermutation(i);
            enumeration.append(SetUtil.constElement(perm));
        }
        set.append(SetUtil.Substitution(enumeration.toString(), "${length}"));
        set.append("</set>\n</sourceSet>");
    	String source = replace(set.toString(), getBaseSourceParameters());
        return set.toString();	
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
