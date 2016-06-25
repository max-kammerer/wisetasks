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
            if (whatToBrush.equals("����"))
                outBrush = new String(" �����");
            else if(whatToBrush.equals("�������"))
                outBrush = new String(" �������");
            else {
                outBrush = new String(" �����");
            }
        }
        else if (count.equalsIgnoreCase("2") || count.equalsIgnoreCase("3") ||
                count.equalsIgnoreCase("4")){
            if (whatToBrush.equals("����"))
                outBrush = new String(" �����");
            else if(whatToBrush.equals("�������"))
                outBrush = new String(" �������");
            else {
                outBrush = new String(" �����");
            }
        }
        else {
            if (whatToBrush.equals("����"))
                outBrush = new String(" �����");
            else if(whatToBrush.equals("�������"))
                outBrush = new String(" ������");
            else
                outBrush = new String(" ������");
        }
        
        if (color.equalsIgnoreCase("�������"))
            outColor = new String(" �������� �����");
        else if (color.equalsIgnoreCase("������"))
            outColor = new String(" ������� �����");
        else if (color.equalsIgnoreCase("�����"))
            outColor = new String(" ������ �����");
        else if (color.equalsIgnoreCase("�������"))
            outColor = new String(" �������� �����");
        else if (color.equalsIgnoreCase("�����"))
            outColor = new String(" ������ �����");
        else if (color.equalsIgnoreCase("������"))
            outColor = new String(" ������� �����");
        else if (color.equalsIgnoreCase("���������"))
            outColor = new String(" ���������� �����");
        else if (color.equalsIgnoreCase("����������"))
            outColor = new String(" ����������� �����");
        else
            outColor = new String(" ����������� �����");
        
        String out = new String(count);
        out = out.concat(outBrush);
        out = out.concat(outColor);
        return out;
    }
    
    public String generateXml() {
        return new String("Not supported yet.");
    }

}
