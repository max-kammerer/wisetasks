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
class PolyhedronCondition implements ComplexElement{

    private String whatToBrush;
    public String figure;
    private String polyType;
    private String corners;
    
    PolyhedronCondition(String whatToBrush, String figure, String polyType, String corners) {
        this.whatToBrush = whatToBrush;
        this.polyType = polyType;
        this.figure = figure;
        this.corners = corners;
    }
    
    public String toDescription() {
        return toString();
    }

    public String generateXml() {
        return "";
    }

    @Override
    public String toString() {
        String outFigure;
        String outBrush;
        if (figure.equals("Пирамида")) {
            outFigure = " пирамиды с ";
            String outCorners = corners;
            outCorners = outCorners.concat(" углами в основании");
            outFigure = outFigure.concat(outCorners);
        }
        else if (figure.equals("Призма")) {
            outFigure = " призмы с ";
            String outCorners = corners;
            outCorners = outCorners.concat(" углами в основании");
            outFigure = outFigure.concat(outCorners);
        }
        else if (figure.equals("Прямоугольный параллелепипед"))
            outFigure = " прямоугольного параллелепипеда";
        else {
            if (polyType.equals("тетраэдр"))
                outFigure = " правильного тетраэдра";
            else if (polyType.equals("куб"))
                outFigure = " куба";
            else if (polyType.equals("додекаэдр"))
                outFigure = " додекаэдра";
            else if (polyType.equals("икосаэдр"))
                outFigure = " икосаэдра";
            else
                outFigure = " октаэдра";
        }
        outBrush = whatToBrush;
        outBrush = outBrush.concat(outFigure);
        return outBrush;
    }
}
