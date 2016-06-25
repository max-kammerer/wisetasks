/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors.figures;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Polygon extends Figure {
    public Polygon(String WhatToBrush, int corners) {
        super(WhatToBrush);
        dim = corners;
        if ((corners % 2) == 1) {
            permCount = 2;
        }
        else {
            permCount = 3;
        }
    }
    @Override
    public ArrayList<Integer> getPermutation(int index) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (index == 0) {
            list.add(Integer.valueOf(dim));
            for (int i = 0; i < dim-1; i++)
                list.add(Integer.valueOf(i+1));
        }
        // вращение по оси через вершины многоугольника
        else if (index == 1){
            list.add(Integer.valueOf(1));
            for (int i = 0; i < dim-1;i++)
                list.add(Integer.valueOf(dim-i));
        }
        else {
            for (int i = 0; i < dim; i++) {
                list.add(Integer.valueOf(dim-i));
            }
        }
        return list;
    }
}
