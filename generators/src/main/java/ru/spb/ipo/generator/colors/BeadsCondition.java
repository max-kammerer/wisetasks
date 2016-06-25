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
public class BeadsCondition implements ComplexElement {
    String color;
    String count;
    public BeadsCondition(String a_color, String a_count) {
        color = a_color;
        count = a_count;
    }
    public String toDescription() {
        return toString();
    }

    @Override
    public String toString() {
        String outColor;
        String outCount;
       if (color.equalsIgnoreCase("красный"))
            outColor = new String("красного цвета");
        else if (color.equalsIgnoreCase("желтый"))
            outColor = new String("желтого цвета");
        else if (color.equalsIgnoreCase("синий"))
            outColor = new String("синего цвета");
        else if (color.equalsIgnoreCase("зеленый"))
            outColor = new String("зеленого цвета");
        else if (color.equalsIgnoreCase("белый"))
            outColor = new String("белого цвета");
        else if (color.equalsIgnoreCase("черный"))
            outColor = new String("черного цвета");
        else if (color.equalsIgnoreCase("оранжевый"))
            outColor = new String("оранжевого цвета");
        else if (color.equalsIgnoreCase("коричневый"))
            outColor = new String("коричневого цвета");
        else
            outColor = new String("фиолетового цвета");
        
        if (count.equalsIgnoreCase("1"))
            outCount = new String("1 бусина ");
        else if (count.equalsIgnoreCase("2") || count.equalsIgnoreCase("3") ||
                count.equalsIgnoreCase("4")) {
            outCount = new String(count);
            outCount = outCount.concat(new String(" бусины "));
        }
        else {
            outCount = new String(count);
            outCount = outCount.concat(new String(" бусин "));
        }
        return outCount.concat(outColor);
    }
    // realize function tag here
    public String generateXml() {
        return new String(" ");
    }
}
