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
       if (color.equalsIgnoreCase("�������"))
            outColor = new String("�������� �����");
        else if (color.equalsIgnoreCase("������"))
            outColor = new String("������� �����");
        else if (color.equalsIgnoreCase("�����"))
            outColor = new String("������ �����");
        else if (color.equalsIgnoreCase("�������"))
            outColor = new String("�������� �����");
        else if (color.equalsIgnoreCase("�����"))
            outColor = new String("������ �����");
        else if (color.equalsIgnoreCase("������"))
            outColor = new String("������� �����");
        else if (color.equalsIgnoreCase("���������"))
            outColor = new String("���������� �����");
        else if (color.equalsIgnoreCase("����������"))
            outColor = new String("����������� �����");
        else
            outColor = new String("����������� �����");
        
        if (count.equalsIgnoreCase("1"))
            outCount = new String("1 ������ ");
        else if (count.equalsIgnoreCase("2") || count.equalsIgnoreCase("3") ||
                count.equalsIgnoreCase("4")) {
            outCount = new String(count);
            outCount = outCount.concat(new String(" ������ "));
        }
        else {
            outCount = new String(count);
            outCount = outCount.concat(new String(" ����� "));
        }
        return outCount.concat(outColor);
    }
    // realize function tag here
    public String generateXml() {
        return new String(" ");
    }
}
