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
public class PolyhedronCondition implements ComplexElement{

    public String whatToBrush;
    public String figure;
    public String polyType;
    public String corners;
    
    public PolyhedronCondition(String whatToBrush, String figure, String polyType, String corners) {
        this.whatToBrush = whatToBrush;
        this.polyType = polyType;
        this.figure = figure;
        this.corners = corners;
    }
    
    public String toDescription() {
        return toString();
    }

    public String generateXml() {
        return new String();
    }

    @Override
    public String toString() {
        String outFigure;
        String outBrush;
        if (figure.equals("��������")) {
            outFigure = new String(" �������� � ");
            String outCorners = new String(corners);
            outCorners = outCorners.concat(" ������ � ���������");
            outFigure = outFigure.concat(outCorners);
        }
        else if (figure.equals("������")) {
            outFigure = new String(" ������ � ");
            String outCorners = new String(corners);
            outCorners = outCorners.concat(" ������ � ���������");
            outFigure = outFigure.concat(outCorners);
        }
        else if (figure.equals("������������� ��������������"))
            outFigure = new String(" �������������� ���������������");
        else {
            if (polyType.equals("��������"))
                outFigure = new String(" ����������� ���������");
            else if (polyType.equals("���"))
                outFigure = new String(" ����");
            else if (polyType.equals("���������"))
                outFigure = new String(" ����������");
            else if (polyType.equals("��������"))
                outFigure = new String(" ���������");
            else
                outFigure = new String(" ��������");
        }
        outBrush = new String(whatToBrush);
        outBrush = outBrush.concat(outFigure);
        return outBrush;
    }
}
