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
public class Triangle extends Figure{
    public Triangle(String WhatToBrush) {
        super(WhatToBrush);
        permCount = 1;
    }
    public ArrayList<Integer> getPermutation(int index) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(3);
        list.add(2);
        return list;
    }

    @Override
    public int getDim() {
        return 3;
    }
}
