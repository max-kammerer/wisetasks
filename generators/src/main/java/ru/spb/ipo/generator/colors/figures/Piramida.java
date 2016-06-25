/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors.figures;

import java.util.ArrayList;

/**
 *
 * @author MGPanteleev
 */
public class Piramida extends Figure{
    public Piramida(String WhatToBrush, int corners) {
        super(WhatToBrush);
        if (WhatToBrush.equals("вершины")) {
            dim = corners + 1;
        }
        else if (WhatToBrush.equals("рёбра")) {
            dim = 2*corners;
        }
        else {
            dim = corners + 1;
        }
        permCount = 1;
    }
    @Override
    public ArrayList<Integer> getPermutation(int index) {
        ArrayList<Integer> list = new ArrayList<Integer> ();
        if (WhatToBrush.equals("вершины") || WhatToBrush.equals("грани")) {
            list.add(Integer.valueOf(dim-1));
            for (int i = 1; i < dim-1; i++) {
                list.add(Integer.valueOf(i));
            }
            list.add(Integer.valueOf(dim));
        }
        else {
            for (int i = 0; i < dim/2-1; i++) {
                list.add(Integer.valueOf(i+2));
            }
            list.add(Integer.valueOf(1));
            for (int i = dim/2; i < dim-1; i++) {
                list.add(Integer.valueOf(i+2));
            }
            list.add(Integer.valueOf(dim/2+1));
        }
        return list;
    }

}
