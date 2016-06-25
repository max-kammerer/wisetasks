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
class PolygonCondition implements ComplexElement {
    private String whatToBrush;
    public String figure;
    private String corners;
    
    PolygonCondition(String whatToBrush, String figure, String corners) {
        this.whatToBrush = whatToBrush;
        this.figure = figure;
        this.corners = corners;
    }
    
    public String toDescription() {
        return toString();
    }

    public String generateXml() {
        return "not supported yet";
    }
    
    @Override
    public String toString() {
        String outFigure;
        String outBrush;
        if (figure.equals("Равнобедренный треугольник"))
            outFigure = " равнобедренного треугольника";
        else if (figure.equals("Прямоугольник"))
            outFigure = " прямоугольника";
        else if (figure.equals("Ромб"))
            outFigure = " ромба";
        else {
            if (corners.equals("3"))
                outFigure = " равностороннего треугольника";
            else if (corners.equals("4"))
                outFigure = " квадрата";
            else {
                outFigure = " правильного ";
                outFigure = outFigure.concat(corners);
                outFigure = outFigure.concat("-угольника");
            }
        }
        outBrush = whatToBrush;
        outBrush = outBrush.concat(outFigure);
        return outBrush;
    }
}
