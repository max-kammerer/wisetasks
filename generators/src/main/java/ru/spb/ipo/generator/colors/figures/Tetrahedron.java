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
public class Tetrahedron extends Figure{
    public Tetrahedron(String WhatToBrush) {
        super(WhatToBrush);
        if (WhatToBrush.equals("вершины") || WhatToBrush.equals("грани"))
            dim = 4;
        else
            dim = 6;
        permCount = 2;
    }
    @Override
    public ArrayList<Integer> getPermutation(int index) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (WhatToBrush.equals("рёбра")) {
            if (index == 0) {
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(5));
                list.add(Integer.valueOf(6));
                list.add(Integer.valueOf(4));
            }
            else {
                list.add(Integer.valueOf(5));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(4));
                list.add(Integer.valueOf(6));
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(3));
            }
        }
        else {
            if (index == 0) {
                list.add(Integer.valueOf(2));
                list.add(Integer.valueOf(3));
                list.add(Integer.valueOf(1));
                list.add(Integer.valueOf(4));
            }
            else {
                if (WhatToBrush.equals("вершины")) {
                    list.add(Integer.valueOf(2));
                    list.add(Integer.valueOf(4));
                    list.add(Integer.valueOf(3));
                    list.add(Integer.valueOf(1));
                }
                else {
                    list.add(Integer.valueOf(1));
                    list.add(Integer.valueOf(3));
                    list.add(Integer.valueOf(4));
                    list.add(Integer.valueOf(2));
                }
            }
        }
        return list;
    }

}
