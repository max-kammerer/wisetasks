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
public class PolygonCondition implements ComplexElement {
    public String whatToBrush;
    public String figure;
    public String corners;
    
    public PolygonCondition(String whatToBrush, String figure, String corners) {
        this.whatToBrush = whatToBrush;
        this.figure = figure;
        this.corners = corners;
    }
    
    public String toDescription() {
        return toString();
    }

    public String generateXml() {
        return new String("not supported yet");
    }
    
    @Override
    public String toString() {
        String outFigure;
        String outBrush;
        if (figure.equals("Равнобедренный треугольник"))
            outFigure = new String(" равнобедренного треугольника");
        else if (figure.equals("Прямоугольник"))
            outFigure = new String(" прямоугольника");
        else if (figure.equals("Ромб"))
            outFigure = new String(" ромба");
        else {
            if (corners.equals("3"))
                outFigure = new String(" равностороннего треугольника");
            else if (corners.equals("4"))
                outFigure = new String(" квадрата");
            else {
                outFigure = new String(" правильного ");
                outFigure = outFigure.concat(corners);
                outFigure = outFigure.concat("-угольника");
            }
        }
        outBrush = new String(whatToBrush);
        outBrush = outBrush.concat(outFigure);
        return outBrush;
    }
}
