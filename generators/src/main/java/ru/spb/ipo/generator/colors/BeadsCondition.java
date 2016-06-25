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
class BeadsCondition implements ComplexElement {
    private String color;
    private String count;
    BeadsCondition(String a_color, String a_count) {
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
            outColor = "красного цвета";
        else if (color.equalsIgnoreCase("желтый"))
            outColor = "желтого цвета";
        else if (color.equalsIgnoreCase("синий"))
            outColor = "синего цвета";
        else if (color.equalsIgnoreCase("зеленый"))
            outColor = "зеленого цвета";
        else if (color.equalsIgnoreCase("белый"))
            outColor = "белого цвета";
        else if (color.equalsIgnoreCase("черный"))
            outColor = "черного цвета";
        else if (color.equalsIgnoreCase("оранжевый"))
            outColor = "оранжевого цвета";
        else if (color.equalsIgnoreCase("коричневый"))
            outColor = "коричневого цвета";
        else
            outColor = "фиолетового цвета";
        
        if (count.equalsIgnoreCase("1"))
            outCount = "1 бусина ";
        else if (count.equalsIgnoreCase("2") || count.equalsIgnoreCase("3") ||
                count.equalsIgnoreCase("4")) {
            outCount = count.concat(" бусины ");
        }
        else {
            outCount = count.concat(" бусин ");
        }
        return outCount.concat(outColor);
    }
    // realize function tag here
    public String generateXml() {
        return " ";
    }
}
