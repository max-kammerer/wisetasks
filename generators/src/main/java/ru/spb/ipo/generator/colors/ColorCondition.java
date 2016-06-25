/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors;

import ru.spb.ipo.generator.base.ComplexElement;

/**
 *
 * @author Admin
 */
public class ColorCondition implements ComplexElement{

    String color;
    String count;
    String whatToBrush;
    public ColorCondition(String color, String count,String whatToBrush) {
        this.color = color;
        this.count = count;
        this.whatToBrush = whatToBrush;
    }
    public String toDescription() {
        return toString();
    }

    @Override
    public String toString() {
        String outColor;
        String outCount;
        String outBrush;
        if (count.equals("1")) {
            if (whatToBrush.equals("рёбра"))
                outBrush = new String(" ребро");
            else if(whatToBrush.equals("вершины"))
                outBrush = new String(" вершина");
            else {
                outBrush = new String(" грань");
            }
        }
        else if (count.equalsIgnoreCase("2") || count.equalsIgnoreCase("3") ||
                count.equalsIgnoreCase("4")){
            if (whatToBrush.equals("рёбра"))
                outBrush = new String(" ребра");
            else if(whatToBrush.equals("вершины"))
                outBrush = new String(" вершины");
            else {
                outBrush = new String(" грани");
            }
        }
        else {
            if (whatToBrush.equals("рёбра"))
                outBrush = new String(" ребер");
            else if(whatToBrush.equals("вершины"))
                outBrush = new String(" вершин");
            else
                outBrush = new String(" граней");
        }
        
        if (color.equalsIgnoreCase("красный"))
            outColor = new String(" красного цвета");
        else if (color.equalsIgnoreCase("желтый"))
            outColor = new String(" желтого цвета");
        else if (color.equalsIgnoreCase("синий"))
            outColor = new String(" синего цвета");
        else if (color.equalsIgnoreCase("зеленый"))
            outColor = new String(" зеленого цвета");
        else if (color.equalsIgnoreCase("белый"))
            outColor = new String(" белого цвета");
        else if (color.equalsIgnoreCase("черный"))
            outColor = new String(" черного цвета");
        else if (color.equalsIgnoreCase("оранжевый"))
            outColor = new String(" оранжевого цвета");
        else if (color.equalsIgnoreCase("коричневый"))
            outColor = new String(" коричневого цвета");
        else
            outColor = new String(" фиолетового цвета");
        
        String out = new String(count);
        out = out.concat(outBrush);
        out = out.concat(outColor);
        return out;
    }
    
    public String generateXml() {
        return new String("Not supported yet.");
    }

}
