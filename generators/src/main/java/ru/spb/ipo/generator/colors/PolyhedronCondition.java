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
        if (figure.equals("Пирамида")) {
            outFigure = new String(" пирамиды с ");
            String outCorners = new String(corners);
            outCorners = outCorners.concat(" углами в основании");
            outFigure = outFigure.concat(outCorners);
        }
        else if (figure.equals("Призма")) {
            outFigure = new String(" призмы с ");
            String outCorners = new String(corners);
            outCorners = outCorners.concat(" углами в основании");
            outFigure = outFigure.concat(outCorners);
        }
        else if (figure.equals("Прямоугольный параллелепипед"))
            outFigure = new String(" прямоугольного параллелепипеда");
        else {
            if (polyType.equals("тетраэдр"))
                outFigure = new String(" правильного тетраэдра");
            else if (polyType.equals("куб"))
                outFigure = new String(" куба");
            else if (polyType.equals("додекаэдр"))
                outFigure = new String(" додекаэдра");
            else if (polyType.equals("икосаэдр"))
                outFigure = new String(" икосаэдра");
            else
                outFigure = new String(" октаэдра");
        }
        outBrush = new String(whatToBrush);
        outBrush = outBrush.concat(outFigure);
        return outBrush;
    }
}
