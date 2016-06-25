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
class ColorCondition implements ComplexElement{

    private String color;
    private String count;
    private String whatToBrush;

    ColorCondition(String color, String count, String whatToBrush) {
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
        String outBrush;
        if (count.equals("1")) {
            if (whatToBrush.equals("рёбра"))
                outBrush = " ребро";
            else if(whatToBrush.equals("вершины"))
                outBrush = " вершина";
            else {
                outBrush = " грань";
            }
        }
        else if (count.equalsIgnoreCase("2") || count.equalsIgnoreCase("3") ||
                count.equalsIgnoreCase("4")){
            if (whatToBrush.equals("рёбра"))
                outBrush = " ребра";
            else if(whatToBrush.equals("вершины"))
                outBrush = " вершины";
            else {
                outBrush = " грани";
            }
        }
        else {
            if (whatToBrush.equals("рёбра"))
                outBrush = " ребер";
            else if(whatToBrush.equals("вершины"))
                outBrush = " вершин";
            else
                outBrush = " граней";
        }
        
        if (color.equalsIgnoreCase("красный"))
            outColor = " красного цвета";
        else if (color.equalsIgnoreCase("желтый"))
            outColor = " желтого цвета";
        else if (color.equalsIgnoreCase("синий"))
            outColor = " синего цвета";
        else if (color.equalsIgnoreCase("зеленый"))
            outColor = " зеленого цвета";
        else if (color.equalsIgnoreCase("белый"))
            outColor = " белого цвета";
        else if (color.equalsIgnoreCase("черный"))
            outColor = " черного цвета";
        else if (color.equalsIgnoreCase("оранжевый"))
            outColor = " оранжевого цвета";
        else if (color.equalsIgnoreCase("коричневый"))
            outColor = " коричневого цвета";
        else
            outColor = " фиолетового цвета";

        return count + outBrush + outColor;
    }
    
    public String generateXml() {
        return "Not supported yet.";
    }

}
