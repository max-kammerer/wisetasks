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
public class Octahedron extends Figure {
    public Octahedron(String WhatToBrush) {
        super(WhatToBrush);
        if (WhatToBrush.equals("вершины"))
            dim = 6;
        else if (WhatToBrush.equals("грани"))
            dim = 8;
        else
            dim = 12;
        permCount = 2;
    }
    @Override
    public ArrayList<Integer> getPermutation(int index) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (WhatToBrush.equals("вершины")) {
            if (index == 0) {
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(5));
                list.add(Integer.valueOf(6));
            }
            else {
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(6));
                list.add(Integer.valueOf(5));
            }
        }
        else  if (WhatToBrush.equals("грани")){
            if (index == 0) {
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(6));
                list.add(Integer.valueOf(7));
                list.add(Integer.valueOf(8));
                list.add(Integer.valueOf(5));
            }
            else {
                list.add(Integer.valueOf(5));
                list.add(Integer.valueOf(8));
                list.add(Integer.valueOf(7));
                list.add(Integer.valueOf(6));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(2));
            }
        }
        else {
            if (index == 0) {
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(6));
                list.add(Integer.valueOf(7));
                list.add(Integer.valueOf(8));
                list.add(Integer.valueOf(5));
                list.add(Integer.valueOf(10));
                list.add(Integer.valueOf(11));
                list.add(Integer.valueOf(12));
                list.add(Integer.valueOf(9));
            }
            else {
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(12));
                list.add(Integer.valueOf(11));
                list.add(Integer.valueOf(10));
                list.add(Integer.valueOf(9));
                list.add(Integer.valueOf(8));
                list.add(Integer.valueOf(7));
                list.add(Integer.valueOf(6));
                list.add(Integer.valueOf(5));
            }
        }
        return list;
    }

}
