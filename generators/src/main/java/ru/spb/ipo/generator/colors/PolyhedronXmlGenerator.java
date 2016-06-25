
package ru.spb.ipo.generator.colors;

import ru.spb.ipo.generator.colors.figures.Icosaedr;
import ru.spb.ipo.generator.colors.figures.Dodecahedron;
import java.util.ArrayList;
import java.util.Map;
import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.SetUtil;
import ru.spb.ipo.generator.colors.figures.Cube;
import ru.spb.ipo.generator.colors.figures.Figure;
import ru.spb.ipo.generator.colors.figures.Octahedron;
import ru.spb.ipo.generator.colors.figures.Parallelepiped;
import ru.spb.ipo.generator.colors.figures.Piramida;
import ru.spb.ipo.generator.colors.figures.Prizma;
import ru.spb.ipo.generator.colors.figures.Tetrahedron;

/**
 *
 * @author Admin
 */
public class PolyhedronXmlGenerator extends BaseGenerator {
    private Figure figure;
    public PolyhedronXmlGenerator(Map sourceParams, Map funcParams, Map taskParams) {
		super(sourceParams, funcParams, taskParams);
    }
    public String generateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Сколькими способами можно раскрасить ");
        sb.append(sourceParams.get("polyhedronText"));
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
    public String getParams() {
        String figureParam = (String)sourceParams.get("polyhedron");
        String brushParam = (String)sourceParams.get("whatToBrush");
        if (figureParam.equals("Правильный многогранник")) {
            String figureType = (String)sourceParams.get("polyType");
            if (figureType.equals("тетраэдр"))
                figure = new Tetrahedron(brushParam);
            else if (figureType.equals("куб"))
                figure = new Cube(brushParam);
            else if (figureType.equals("октаэдр"))
                figure = new Octahedron(brushParam);
            else if (figureType.equals("икосаэдр"))
                figure = new Icosaedr(brushParam);
            else 
                figure = new Dodecahedron(brushParam);
        }
        else if (figureParam.equals("Пирамида")) {
            String corners = (String)sourceParams.get("corners");
            figure = new Piramida(brushParam,Integer.valueOf(corners));
        }
        else if (figureParam.equals("Призма")) {
            String corners = (String)sourceParams.get("corners");
            figure = new Prizma(brushParam,Integer.valueOf(corners));
        }
        // Значит параллелепипед
        else {
            figure = new Parallelepiped(brushParam);
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
        }StringBuilder enumeration = new StringBuilder();
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
