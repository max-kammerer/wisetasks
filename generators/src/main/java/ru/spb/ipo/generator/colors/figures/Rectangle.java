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
public class Rectangle extends Figure{

    public Rectangle(String WhatToBrush) {
        super(WhatToBrush);
        permCount = 2;
    }
    @Override
    public int getDim() {
        return 4;
    }

    @Override
    public ArrayList<Integer> getPermutation(int index) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (index == 0) {
            list.add(Integer.valueOf(3));
            list.add(Integer.valueOf(4));
            list.add(Integer.valueOf(1));
            list.add(Integer.valueOf(2));
        }
        else {
            if (WhatToBrush.equals("вершины")) {
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(1));
            }
            else {
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(4));
            }
        }
        return list;
    }

}
